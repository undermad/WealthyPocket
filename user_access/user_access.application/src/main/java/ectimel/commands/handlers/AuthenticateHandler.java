package ectimel.commands.handlers;

import ectimel.commands.Authenticate;
import ectimel.cqrs.commands.Handler;
import ectimel.cqrs.commands.ResultCommandHandler;
import ectimel.dto.LoginResponse;
import ectimel.repositories.UserRepository;
import ectimel.services.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Handler
public class AuthenticateHandler implements ResultCommandHandler<Authenticate, LoginResponse> {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    public AuthenticateHandler(UserRepository repository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse send(Authenticate command) {

//        var user = repository.getAsync(new Email(command.email())).join();
//        if(user.validatePassword(new Password(passwordEncoder.encode(command.password())))) {
//            System.out.println(command.password());
//            System.out.println(user.getPassword().value());
//        }
        
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                command.email(),
                command.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginResponse(jwtProvider.generateToken(command.email()), "Bearer");
        
        
//        throw new UnauthorizedException();
    }

}
