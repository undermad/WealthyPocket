package user_access.persistence.outbox;

import wallet.message_broker.Event;
import wallet.outbox.OutboxRepository;
import wallet.utils.JsonMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userAccessOutbox")
public class PostgresOutboxRepository implements OutboxRepository<UserAccessOutboxMessage> {

    @PersistenceContext(unitName = "puWriteUserAccess")
    private EntityManager entityManager;
  
    @Override
    public void saveMessage(Event event) {
        var message = UserAccessOutboxMessage.builder()
                .eventId(event.getId())
                .eventType(String.valueOf(event.getClass()).split(" ")[1])
                .payload(JsonMapper.toJson(event))
                .processed(false)
                .processedAt(null)
                .build();

        entityManager.persist(message);
    }

    @Override
    public List<UserAccessOutboxMessage> getAllMessages() {
        return entityManager.createQuery("""
                    SELECT o FROM UserAccessOutboxMessage o WHERE o.processed = false
                    """, UserAccessOutboxMessage.class).getResultList();
    }

    @Override
    public void updateMessage(UserAccessOutboxMessage message) {
        entityManager.merge(message);
    }

}
