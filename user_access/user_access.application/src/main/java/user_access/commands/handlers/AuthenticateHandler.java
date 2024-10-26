package user_access.commands.handlers;

import ectimel.cqrs.commands.Handler;
import ectimel.cqrs.commands.ResultCommandHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ua_parser.Client;
import ua_parser.Parser;
import user_access.commands.Authenticate;
import user_access.dto.LoginResponse;
import user_access.entities.User;
import user_access.factories.DeviceFingerprintData;
import user_access.repositories.UserRepository;
import user_access.services.JwtProvider;
import user_access.value_objects.*;

@Handler
public class AuthenticateHandler implements ResultCommandHandler<Authenticate, LoginResponse>
{

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticateHandler(JwtProvider jwtProvider, AuthenticationManager authenticationManager, UserRepository userRepository)
    {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse send(Authenticate command)
    {
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

        if(!user.isFingerprintKnew(fingerprint)) {

            
        }
        
        
        return new LoginResponse(jwtProvider.generateToken(command.email()), "Bearer", false);
    }

    private DeviceFingerprintData createDeviceFingerprintData(Authenticate command, User user)
    {
        var ipAddress = new IpAddress(command.request().getRemoteAddr());

        var parser = new Parser();
        Client client = parser.parse(command.request().getHeader("user-agent"));
        var ua = client.userAgent;
        var userAgent = new UserAgent(ua.family + " " + ua.major + " " + ua.minor);

        var os = client.os;
        var operatingSystem = new OperatingSystem(os.family + " " + os.major + " " + os.minor);

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
