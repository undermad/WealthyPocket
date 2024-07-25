package ectimel.outbox;

import ectimel.message_broker.Event;

import java.util.List;

public interface OutboxRepository<T> {
    void saveMessage(Event event);
    List<T> getAllMessages();
    
    
}
