package ectimel.commands;

import ectimel.cqrs.commands.ResultCommand;
import ectimel.dto.LoginResponse;

public record Authenticate(String email, String password) implements ResultCommand<LoginResponse> {
}
