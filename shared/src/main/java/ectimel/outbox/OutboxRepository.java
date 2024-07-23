package ectimel.outbox;

import ectimel.message_broker.Event;
import ectimel.utils.JsonMapper;
import jakarta.persistence.EntityManager;

import java.time.Instant;
import java.util.UUID;

public interface OutboxRepository {
    
    void saveMessage(Event event);
    
    default void save(Event event, EntityManager entityManager) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("""
                        INSERT INTO outbox (id, event_type, payload, processed, processed_at, createdon)
                        VALUES (:id, :eventType, CAST(:payload AS jsonb), :processed, :processedAt, :createdon)
                        """);
        entityManager.createNativeQuery(stringBuilder.toString())
                .setParameter("id", UUID.randomUUID())
                .setParameter("eventType", event.getClass())
                .setParameter("payload", JsonMapper.toJson(event))
                .setParameter("processed", false)
                .setParameter("processedAt", null)
                .setParameter("createdon", Instant.now())
                .executeUpdate();
    }
    
}
