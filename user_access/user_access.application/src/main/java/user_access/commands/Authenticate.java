package user_access.commands;

import wallet.cqrs.commands.ResultCommand;
import user_access.dto.LoginResponse;

public record Authenticate(String email, String password) implements ResultCommand<LoginResponse> {
}
