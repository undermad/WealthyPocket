package wallet.events_handlers;

import wallet.cqrs.commands.CommandDispatcher;
import wallet.message_broker.EventController;
import wallet.message_broker.EventListener;
import user_access.UserRegisteredEvent;
import wallet.commands.CreateOwner;

@EventController(inboxRepositoryBean = "walletInboxRepository")
public class WalletEventHandlers {
    
    private final CommandDispatcher commandDispatcher;

    public WalletEventHandlers(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        var command = new CreateOwner(event.userId(), event.country());
        commandDispatcher.send(command);
    }
    
}
