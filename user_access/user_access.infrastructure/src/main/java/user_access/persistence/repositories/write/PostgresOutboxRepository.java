package user_access.persistence.repositories.write;

import ectimel.message_broker.Event;
import ectimel.outbox.OutboxRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository("userAccessOutbox")
public class PostgresOutboxRepository implements OutboxRepository {

    @PersistenceContext(unitName = "puWriteUserAccess")
    private final EntityManager entityManager;

    public PostgresOutboxRepository(@Qualifier("readEntityManagerFactoryUserAccess") EntityManager entityManager) {
        this.entityManager = entityManager;
    }
  
    @Override
    public void saveMessage(Event event) {
        save(event, entityManager);
    }

}
