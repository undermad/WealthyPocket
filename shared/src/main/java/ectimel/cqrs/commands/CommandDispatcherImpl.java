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
        var commandName = getCommandBeanName(command);
        
        var commandHandlerBean = applicationContext.getBean(commandName + "Handler");
        if(commandHandlerBean instanceof CommandHandler<?>) {
            var handler = (CommandHandler<TCommand>) commandHandlerBean;
            handler.handleCommand(command);
        } 
        
        throw new HandlerNotFoundException(commandName);
        
    }
    
    private String getCommandBeanName(Command command) {
        var commandName = command.getClass().getName();
        commandName = commandName.substring(0, 1).toLowerCase() + commandName.substring(1);
        return commandName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
