package ectimel.inbox;

import ectimel.message_broker.Event;

import java.util.List;

public interface InboxRepository<T> {
    void saveMessage(Event event);
    List<T> getAllMessages();
}
