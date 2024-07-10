package ectimel.cqrs;

public interface CommandHandler<TCommand extends Command> {
    void handleCommand(TCommand command);
}
