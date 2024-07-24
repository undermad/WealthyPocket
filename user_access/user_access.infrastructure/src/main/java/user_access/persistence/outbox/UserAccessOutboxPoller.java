package user_access.persistence.outbox;

import ectimel.message_broker.MessageBroker;
import ectimel.outbox.Poller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class UserAccessOutboxPoller implements Poller {
    
    @PersistenceContext(unitName = "puReadUserAccess")
    private EntityManager entityManager;
    
    private final MessageBroker messageBroker;

    public UserAccessOutboxPoller(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public void poll() {
    }
}
