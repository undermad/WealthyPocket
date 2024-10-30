package wallet.cqrs.commands;

public interface CommandDispatcher {

    <TCommand extends Command> 
    void send(TCommand command);
    
    <TResult> TResult sendWithResult(ResultCommand<TResult> command);
    
    
}
