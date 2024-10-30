package user_access.commands;

import wallet.cqrs.commands.Command;

import java.util.UUID;

public record ChangePassword(UUID userId, String oldPassword, String newPassword) implements Command {
}
