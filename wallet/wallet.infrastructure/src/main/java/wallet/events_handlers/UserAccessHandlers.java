package wallet.events_handlers;

import ectimel.inbox.InboxRepository;
import ectimel.message_broker.EventController;
import ectimel.message_broker.EventListener;
import org.springframework.beans.factory.annotation.Qualifier;
import user_access.UserRegisteredEvent;
import wallet.persistence.inbox.WalletInboxMessage;

@EventController
public class UserAccessHandlers {
    
    // Need to refactor this, handler suppose to handle actual logic, and inbox should be automated behind the hood
    
    private final InboxRepository<WalletInboxMessage> inboxRepository;

    public UserAccessHandlers(@Qualifier("walletInboxRepository") InboxRepository<WalletInboxMessage> inboxRepository) {
        this.inboxRepository = inboxRepository;
    }


    @EventListener(UserRegisteredEvent.class)
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        inboxRepository.saveMessage(event);
    }
    
}
