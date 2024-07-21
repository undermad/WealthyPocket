package ectimel.outbox;

import ectimel.message_broker.Event;

public interface OutboxRepository {
    void save(Event event);
    
}
