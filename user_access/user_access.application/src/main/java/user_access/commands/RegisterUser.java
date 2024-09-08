package user_access.commands;

import ectimel.cqrs.commands.Command;

import java.time.LocalDate;

public record RegisterUser(String email, String password, LocalDate bornDate, String country) implements Command {
}
