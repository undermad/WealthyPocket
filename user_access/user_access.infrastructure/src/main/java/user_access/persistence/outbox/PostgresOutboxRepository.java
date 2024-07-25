package user_access.persistence.outbox;

import ectimel.message_broker.Event;
import ectimel.outbox.OutboxRepository;
import ectimel.utils.JsonMapper;
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

}
