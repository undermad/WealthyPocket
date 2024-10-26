package user_access.commands;

import ectimel.cqrs.commands.ResultCommand;
import jakarta.servlet.http.HttpServletRequest;
import user_access.dto.LoginResponse;

import java.net.http.HttpRequest;

public record Authenticate(String email, String password, HttpServletRequest request) implements ResultCommand<LoginResponse> {
}
