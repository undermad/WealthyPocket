package wallet.events_handlers;

import ectimel.cqrs.commands.CommandDispatcher;
import ectimel.message_broker.EventController;
import ectimel.message_broker.EventListener;
import user_access.UserRegisteredEvent;
import wallet.commands.CreateOwner;

@EventController(inboxRepositoryBean = "walletInboxRepository")
public class UserAccessHandlers {
    
    private CommandDispatcher commandDispatcher;

    public UserAccessHandlers(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        var command = new CreateOwner(event.userId());
        commandDispatcher.send(command);
    }
    
}
