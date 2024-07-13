package ectimel.cqrs.commands;

public interface CommandDispatcher {

    <TCommand extends Command> 
    void send(TCommand command);
    
    <TResult> TResult send(ResultCommand<TResult> command);
    
    
}
