package user_access.persistence.repositories.write;

import ectimel.message_broker.Event;
import ectimel.outbox.OutboxRepository;
import ectimel.utils.JsonMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository("userAccessOutbox")
public class PostgresOutboxRepository implements OutboxRepository {

    @PersistenceContext(unitName = "puWriteUserAccess")
    private final EntityManager entityManager;
    
    private final JsonMapper jsonMapper;

    private final String SCHEMA_NAME = "user_access";

    public PostgresOutboxRepository(@Qualifier("readEntityManagerFactoryUserAccess") EntityManager entityManager, JsonMapper jsonMapper) {
        this.entityManager = entityManager;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void save(Event event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO " + SCHEMA_NAME);
        stringBuilder.append("""
                        .outbox (id, event_type, payload, processed, processed_at, createdon)
                        VALUES (:id, :aggregateId, :aggregateType, :eventType, :payload::jsonb, :processed, :processedAt, :createdon)
                        """);
        entityManager.createNativeQuery(stringBuilder.toString())
                .setParameter("id", UUID.randomUUID())
                .setParameter("eventType", event.getClass())
                .setParameter("payload", jsonMapper.toJson(event))
                .setParameter("processed", false)
                .setParameter("processedAt", null)
                .setParameter("createdon", Instant.now())
                .executeUpdate();
    }
    
    
    
    
}
