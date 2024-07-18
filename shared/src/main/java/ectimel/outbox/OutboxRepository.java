package ectimel.outbox;

import jakarta.persistence.EntityManager;

public interface OutboxRepository {
    void save(EntityManager entityManager, String schemaName);
}
