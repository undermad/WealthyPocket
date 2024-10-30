package wallet.commands;

import wallet.cqrs.commands.Command;

import java.util.UUID;

public record CreateOwner(UUID userId, String country) implements Command {
}
