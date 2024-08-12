package wallet.commands;

import ectimel.cqrs.commands.Command;

import java.util.UUID;

public record CreateOwner(UUID userId) implements Command {
}
