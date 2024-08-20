package user_access.persistence.inbox;

import ectimel.inbox.InboxRepository;
import ectimel.message_broker.Event;
import ectimel.utils.JsonMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("userAccessInbox")
public class PostgresInboxRepository implements InboxRepository<UserAccessInboxMessage> {
    
    @PersistenceContext(unitName = "puWriteUserAccess")
    private EntityManager entityManager;
    
    @Override
    public void saveMessage(Event event) {
        var message = UserAccessInboxMessage.builder()
                .eventType(String.valueOf(event.getClass()).split(" ")[1])
                .payload(JsonMapper.toJson(event))
                .processed(false)
                .processedAt(null)
                .build();
        
        entityManager.persist(message);
    }

    @Override
    public UserAccessInboxMessage getMessage(UUID id) {
        return null;
    }

    @Override
    public void updateMessage(UserAccessInboxMessage message) {
        entityManager.merge(message);
    }


}
