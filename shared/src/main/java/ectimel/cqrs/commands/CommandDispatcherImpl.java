package ectimel.cqrs.commands;

import ectimel.exceptions.HandlerNotFoundException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CommandDispatcherImpl implements CommandDispatcher, ApplicationContextAware {
    
    
    private ApplicationContext applicationContext;
    
    @SuppressWarnings("unchecked")
    @Override
    public <TCommand extends Command> void send(TCommand command) {
        var commandHandlerBean = getHandlerBean(command);
        
        if(commandHandlerBean instanceof CommandHandler<?>) {
            var handler = (CommandHandler<TCommand>) commandHandlerBean;
            handler.handle(command);
            return;
        } 
        
        throw new HandlerNotFoundException(command.getClass().getName());
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public <TResult> TResult send(ResultCommand<TResult> command) {
        var commandHandlerBean = getHandlerBean(command);
        
        if(commandHandlerBean instanceof ResultCommandHandler<?,?>) {
            var handler = (ResultCommandHandler<ResultCommand<TResult>, TResult>) commandHandlerBean;
            return handler.send(command);
        }
        
        throw new HandlerNotFoundException(command.getClass().getName());
    }

    private Object getHandlerBean(Command command) {
        var commandName = command.getClass().getSimpleName();
        commandName = commandName.substring(0, 1).toLowerCase() + commandName.substring(1);
        return applicationContext.getBean(commandName + "Handler");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
