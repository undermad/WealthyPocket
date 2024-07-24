package user_access.persistence.outbox;

import ectimel.message_broker.Event;
import ectimel.outbox.OutboxRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository("userAccessOutbox")
public class PostgresOutboxRepository implements OutboxRepository {

    @PersistenceContext(unitName = "puWriteUserAccess")
    private EntityManager entityManager;
  
    @Override
    public void saveMessage(Event event) {
//        var message = OutboxMessage.builder()
//                .eventType(String.valueOf(event.getClass()))
//                .payload()
//                .processed(false)
//                .processedAt(null)
//                .build();
//        
//        entityManager.persist(message);
        
        save(event, entityManager);
    }

}
