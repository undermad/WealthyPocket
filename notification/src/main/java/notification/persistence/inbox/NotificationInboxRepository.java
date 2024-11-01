package notification.persistence.inbox;


import wallet.inbox.InboxRepository;
import wallet.message_broker.Event;
import wallet.utils.JsonMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository("notificationInboxRepository")
public class NotificationInboxRepository implements InboxRepository<NotificationInboxMessage> {
    
    @PersistenceContext(unitName = "puWriteNotification")
    private EntityManager entityManager;
    
    @Transactional("writeTransactionManagerNotification")
    @Override
    public void saveMessage(Event event) {
        var message = NotificationInboxMessage.builder()
                .eventId(event.getId())
                .eventType(String.valueOf(event.getClass()).split(" ")[1])
                .payload(JsonMapper.toJson(event))
                .processed(false)
                .processedAt(null)
                .build();
        
        entityManager.persist(message);
    }

    @Override
    public NotificationInboxMessage getMessage(UUID id) {
        return null;
    }

    @Override
    public NotificationInboxMessage getMessageByEventId(UUID eventId) {

        var query = entityManager.createQuery("""
                SELECT m FROM NotificationInboxMessage m WHERE m.eventId = :eventId
                """, NotificationInboxMessage.class);

        query.setParameter("eventId", eventId);

        var result = query.getResultList();

        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public List<NotificationInboxMessage> getAllMessages() {
        var query = entityManager.createQuery("""
                SELECT m FROM NotificationInboxMessage m WHERE m.processed = false
                """, NotificationInboxMessage.class);

        var result = query.getResultList();

        return result;
    }

    @Transactional("writeTransactionManagerNotification")
    @Override
    public void updateMessage(NotificationInboxMessage message) {
        entityManager.merge(message);
    }
}
