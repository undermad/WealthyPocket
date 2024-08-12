package user_access.commands.handlers;

import ectimel.cqrs.commands.Handler;
import ectimel.cqrs.commands.ResultCommandHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import user_access.commands.Authenticate;
import user_access.dto.LoginResponse;
import user_access.services.JwtProvider;

@Handler
public class AuthenticateHandler implements ResultCommandHandler<Authenticate, LoginResponse> {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticateHandler(JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse send(Authenticate command) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                command.email(),
                command.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginResponse(jwtProvider.generateToken(command.email()), "Bearer");
    }

}
