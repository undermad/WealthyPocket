package user_access.commands;

import ectimel.cqrs.commands.ResultCommand;
import user_access.dto.LoginResponse;

public record Authenticate(String email, String password, String deviceFingerprint) implements ResultCommand<LoginResponse> {
}
