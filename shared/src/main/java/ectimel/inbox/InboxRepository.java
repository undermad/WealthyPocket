package ectimel.inbox;

import ectimel.message_broker.Event;

import java.util.List;
import java.util.UUID;

public interface InboxRepository<T> {
    void saveMessage(Event event);

    T getMessage(UUID id);

    void updateMessage(T message);
    
    
}
