package wallet.outbox;

import wallet.message_broker.Event;

import java.util.List;

public interface OutboxRepository<T> {
    void saveMessage(Event event);
    List<T> getAllMessages();
    
    void updateMessage(T message);
    
}
