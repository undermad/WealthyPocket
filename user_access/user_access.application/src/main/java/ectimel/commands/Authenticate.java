package ectimel.commands;

import ectimel.cqrs.commands.Command;

public record Authenticate(String email, String password) implements Command {
}
