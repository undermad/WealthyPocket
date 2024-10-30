package wallet.persistence.outbox;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import wallet.message_broker.Event;
import wallet.outbox.OutboxRepository;
import wallet.utils.JsonMapper;

import java.util.List;


@Repository("walletOutbox")
public class PostgresOutboxRepository implements OutboxRepository<WalletOutboxMessage> {

    @PersistenceContext(unitName = "puWriteWallet")
    private EntityManager entityManager;
  
    @Override
    public void saveMessage(Event event) {
        var message = WalletOutboxMessage.builder()
                .eventId(event.getId())
                .eventType(String.valueOf(event.getClass()).split(" ")[1])
                .payload(JsonMapper.toJson(event))
                .processed(false)
                .processedAt(null)
                .build();

        entityManager.persist(message);
    }

    @Override
    public List<WalletOutboxMessage> getAllMessages() {
        return entityManager.createQuery("""
                    SELECT o FROM WalletOutboxMessage o WHERE o.processed = false
                    """, WalletOutboxMessage.class).getResultList();
    }

    @Override
    public void updateMessage(WalletOutboxMessage message) {
        entityManager.merge(message);
    }

}
