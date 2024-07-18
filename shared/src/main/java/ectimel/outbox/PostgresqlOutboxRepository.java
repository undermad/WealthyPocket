package ectimel.outbox;

import jakarta.persistence.EntityManager;

public class PostgresqlOutboxRepository implements OutboxRepository {
    
    @Override
    public void save(EntityManager entityManager, String schemaName) {

        // 
    }
}
