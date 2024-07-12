package ectimel.cqrs.commands;

public interface CommandHandler<TCommand extends Command> {
    void handleCommand(TCommand command);
}
