package notification.infrastructure.persistance;


import ectimel.inbox.InboxRepository;
import ectimel.message_broker.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("notificationInboxRepository")
public class NotificationInboxRepository implements InboxRepository<NotificationInboxMessage> {
    
    @Override
    public void saveMessage(Event event) {
        
    }

    @Override
    public NotificationInboxMessage getMessage(UUID id) {
        return null;
    }

    @Override
    public NotificationInboxMessage getMessageByEventId(UUID eventId) {
        return null;
    }

    @Override
    public List<NotificationInboxMessage> getAllMessages() {
        return null;
    }

    @Override
    public void updateMessage(NotificationInboxMessage message) {

    }
}
