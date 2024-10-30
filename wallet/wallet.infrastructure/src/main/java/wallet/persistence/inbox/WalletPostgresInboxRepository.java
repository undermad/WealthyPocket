package wallet.persistence.inbox;


import wallet.inbox.InboxRepository;
import wallet.message_broker.Event;
import wallet.utils.JsonMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Repository("walletInboxRepository")
public class WalletPostgresInboxRepository implements InboxRepository<WalletInboxMessage> {
    
    @PersistenceContext(unitName = "puWriteWallet")
    private EntityManager entityManager;

    @Transactional("writeTransactionManagerWallet")
    @Override
    public void saveMessage(Event event) {
        var message = WalletInboxMessage.builder()
                .eventId(event.getId())
                .eventType(String.valueOf(event.getClass()).split(" ")[1])
                .payload(JsonMapper.toJson(event))
                .processed(false)
                .processedAt(null)
                .build();

        entityManager.persist(message);
    }

    @Override
    public WalletInboxMessage getMessage(UUID id) {
        return null;
    }

    @Override
    public WalletInboxMessage getMessageByEventId(UUID eventId) {

        var query = entityManager.createQuery("""
                SELECT m FROM WalletInboxMessage m WHERE m.eventId = :eventId
                """, WalletInboxMessage.class);

        query.setParameter("eventId", eventId);

        var result = query.getResultList();

        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public List<WalletInboxMessage> getAllMessages() {
        var query = entityManager.createQuery("""
                SELECT m FROM WalletInboxMessage m WHERE m.processed = false
                """, WalletInboxMessage.class);

        var result = query.getResultList();

        return result;
    }

    @Transactional("writeTransactionManagerWallet")
    @Override
    public void updateMessage(WalletInboxMessage message) {
        entityManager.merge(message);
    }

}
