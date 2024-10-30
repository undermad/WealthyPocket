package user_access.persistence.outbox;

import wallet.message_broker.Event;
import wallet.message_broker.MessageBroker;
import wallet.outbox.OutboxRepository;
import wallet.outbox.Poller;
import wallet.utils.JsonMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.concurrent.ExecutionException;


@Component("userAccessOutboxPoller")
public class OutboxPoller implements Poller {

    private final MessageBroker messageBroker;
    private final OutboxRepository<UserAccessOutboxMessage> outboxRepository;

    public OutboxPoller(MessageBroker messageBroker, OutboxRepository<UserAccessOutboxMessage> outboxRepository) {
        this.messageBroker = messageBroker;
        this.outboxRepository = outboxRepository;
    }

    @Transactional("writeTransactionManagerUserAccess")
    @Scheduled(fixedRate = 5000L)
    public void pollSchedule() {
        poll();
    }


    @Override
    public void poll() {
        var messages = outboxRepository.getAllMessages();
        if (messages.isEmpty()) return;
        messages.forEach(message -> {
            if (message instanceof UserAccessOutboxMessage msg) {
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
