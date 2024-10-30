package wallet.inbox;

import wallet.message_broker.Event;

import java.util.List;
import java.util.UUID;

public interface InboxRepository<T> {
    void saveMessage(Event event);

    T getMessage(UUID id);
    T getMessageByEventId(UUID eventId);
    
    List<T> getAllMessages();

    void updateMessage(T message);
    
    
}
