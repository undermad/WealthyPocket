package user_access.commands;

import ectimel.cqrs.commands.ResultCommand;
import user_access.dto.EmailDto;

public record ValidateJwt(String token) implements ResultCommand<EmailDto> {
}
