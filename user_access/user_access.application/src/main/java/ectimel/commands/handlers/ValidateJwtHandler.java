package ectimel.commands.handlers;

import ectimel.commands.ValidateJwt;
import ectimel.cqrs.commands.Handler;
import ectimel.cqrs.commands.ResultCommandHandler;
import ectimel.dto.EmailDto;
import ectimel.exceptions.UnauthorizedException;
import ectimel.services.JwtProvider;

@Handler
public class ValidateJwtHandler implements ResultCommandHandler<ValidateJwt, EmailDto> {

    private final JwtProvider jwtProvider;

    public ValidateJwtHandler(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public EmailDto send(ValidateJwt command) {
        if (jwtProvider.validateToken(command.token())) {
            return new EmailDto(jwtProvider.getSubject(command.token()));
        }
        throw new UnauthorizedException();
    }
}
