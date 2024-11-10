package user_access.commands.handlers;

import ectimel.cqrs.commands.Handler;
import ectimel.cqrs.commands.ResultCommandHandler;
import ectimel.outbox.OutboxMessage;
import ectimel.outbox.OutboxRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import ua_parser.Client;
import ua_parser.Parser;
import user_access.LoginFromNewDeviceEvent;
import user_access.commands.Authenticate;
import user_access.dto.LoginResponse;
import user_access.entities.User;
import user_access.factories.DeviceFingerprintData;
import user_access.factories.DeviceFingerprintFactory;
import user_access.repositories.UserRepository;
import user_access.services.JwtProvider;
import user_access.value_objects.*;

import java.util.UUID;

@Handler
public class AuthenticateHandler implements ResultCommandHandler<Authenticate, LoginResponse>
{

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final OutboxRepository<OutboxMessage> outboxRepository;

    public AuthenticateHandler(JwtProvider jwtProvider,
                               AuthenticationManager authenticationManager,
                               UserRepository userRepository,
                               @Qualifier("userAccessOutbox") OutboxRepository outboxRepository)
    {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.outboxRepository = outboxRepository;
    }

    @Transactional("writeTransactionManagerUserAccess")
    @Override
    public LoginResponse send(Authenticate command)
    {
        // Behavior to change:
        //  add logic that will approve device after OTP is activated
        //  
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                command.email(),
                command.password())
        );
        var user = userRepository.get(new Email(command.email()));
        var deviceFingerprintData = createDeviceFingerprintData(command, user);

        var fingerprint = Fingerprint.createFingerprint(deviceFingerprintData.ipAddress(),
                deviceFingerprintData.userAgent(),
                deviceFingerprintData.operatingSystem(),
                deviceFingerprintData.device());

        if (user.isFingerprintVerified(fingerprint)) {
            user.updateLastLogin(fingerprint);
            return new LoginResponse(jwtProvider.generateToken(command.email()), "Bearer", false);
        }

        var deviceFingerprint = DeviceFingerprintFactory.createFingerprint(deviceFingerprintData);
        user.getDeviceFingerprints().add(deviceFingerprint);
        userRepository.update(user);

        var userAttemptToLoginEvent = LoginFromNewDeviceEvent.builder()
                .id(UUID.randomUUID())
                .email(user.getEmail().value())
                .build();
        
        outboxRepository.saveMessage(userAttemptToLoginEvent);
        
        return new LoginResponse("", "", true);
    }
    

    private DeviceFingerprintData createDeviceFingerprintData(Authenticate command, User user)
    {
        var ipAddress = new IpAddress(command.request().getRemoteAddr());

        var parser = new Parser();
        Client client = parser.parse(command.request().getHeader("user-agent"));
        var ua = client.userAgent;
        var userAgent = new UserAgent(ua.family + " " + ua.major + "." + ua.minor);

        var os = client.os;
        var operatingSystem = new OperatingSystem(os.family + " " + os.major + "." + os.minor);

        var device = new Device(client.device.family);

        return DeviceFingerprintData.builder()
                .user(user)
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .operatingSystem(operatingSystem)
                .device(device)
                .build();
    }


}
