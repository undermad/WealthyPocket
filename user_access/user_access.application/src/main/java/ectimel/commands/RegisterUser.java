package ectimel.commands;

import ectimel.cqrs.commands.Command;

public record RegisterUser(String email, String password) implements Command {
}
