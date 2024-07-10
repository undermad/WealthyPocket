package ectimel.commands;

import ectimel.cqrs.Command;

public record RegisterUser(String email, String encryptedPassword) implements Command {
}
