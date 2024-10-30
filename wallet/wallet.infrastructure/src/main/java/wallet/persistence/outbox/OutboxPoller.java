package wallet.persistence.outbox;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wallet.message_broker.Event;
import wallet.message_broker.MessageBroker;
import wallet.outbox.OutboxRepository;
import wallet.outbox.Poller;
import wallet.utils.JsonMapper;

import java.time.Instant;
import java.util.concurrent.ExecutionException;


@Component("walletOutboxPoller")
public class OutboxPoller implements Poller {

    private final MessageBroker messageBroker;
    private final OutboxRepository<WalletOutboxMessage> outboxRepository;

    public OutboxPoller(MessageBroker messageBroker, OutboxRepository<WalletOutboxMessage> outboxRepository) {
        this.messageBroker = messageBroker;
        this.outboxRepository = outboxRepository;
    }

    @Transactional("writeTransactionManagerWallet")
    @Scheduled(fixedRate = 5000L)
    public void pollSchedule() {
        poll();
    }


    @Override
    public void poll() {
        var messages = outboxRepository.getAllMessages();
        if (messages.isEmpty()) return;
        messages.forEach(message -> {
            if (message instanceof WalletOutboxMessage msg) {
                try {
                    Class<?> clazz = Class.forName(msg.getEventType());
                    var event = (Event) JsonMapper.fromJson(msg.getPayload(), clazz);
                    messageBroker.publish(event);
                    message.setProcessed(true);
                    message.setProcessedAt(Instant.now());
                    outboxRepository.updateMessage(message);
                } catch (ClassNotFoundException | ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
