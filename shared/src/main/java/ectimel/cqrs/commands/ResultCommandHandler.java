package ectimel.cqrs.commands;

public interface ResultCommandHandler<TResultCommand extends ResultCommand<TResult>, TResult>{
    TResult send(TResultCommand command);
}
