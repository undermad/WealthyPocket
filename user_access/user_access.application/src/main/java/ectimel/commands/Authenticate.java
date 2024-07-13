package ectimel.commands;

import ectimel.cqrs.commands.ResultCommand;

public record Authenticate(String email, String password) implements ResultCommand<Boolean> {
}
