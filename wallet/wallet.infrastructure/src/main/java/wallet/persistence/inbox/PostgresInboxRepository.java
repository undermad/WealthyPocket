package wallet.persistence.inbox;


import ectimel.inbox.InboxRepository;
import ectimel.message_broker.Event;
import ectimel.utils.JsonMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("walletInboxRepository")
public class PostgresInboxRepository implements InboxRepository<WalletInboxMessage> {
    
    @PersistenceContext(unitName = "puWriteWallet")
    private EntityManager entityManager;

    @Transactional("writeTransactionManagerWallet")
    @Override
    public void saveMessage(Event event) {
        var message = WalletInboxMessage.builder()
                .eventType(String.valueOf(event.getClass()).split(" ")[1])
                .payload(JsonMapper.toJson(event))
                .processed(false)
                .processedAt(null)
                .build();

        entityManager.persist(message);
    }

    @Override
    public List<WalletInboxMessage> getAllMessages() {
        return entityManager.createQuery("""
                    SELECT m FROM WalletInboxMessage m WHERE m.processed = false
                    """, WalletInboxMessage.class).getResultList();
    }
}
