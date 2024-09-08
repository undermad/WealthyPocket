package wallet.commands;

import ectimel.cqrs.commands.Command;

import java.time.LocalDate;
import java.util.UUID;

public record CreateOwner(UUID userId, String country) implements Command {
}
