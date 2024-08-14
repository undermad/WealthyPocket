package user_access.commands;

import ectimel.cqrs.commands.Command;

import java.util.UUID;

public record ChangePassword(UUID uuid,String oldPassword, String newPassword) implements Command {
}
