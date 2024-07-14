package ectimel.commands;

import ectimel.cqrs.commands.ResultCommand;
import ectimel.dto.EmailDto;

public record ValidateJwt(String token) implements ResultCommand<EmailDto> {
}
