package user_access.commands.handlers;

import user_access.commands.ValidateJwt;
import ectimel.cqrs.commands.Handler;
import ectimel.cqrs.commands.ResultCommandHandler;
import user_access.dto.EmailDto;
import user_access.exceptions.UnauthorizedException;
import user_access.services.JwtProvider;

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
