package notification.infrastructure.event_handlers;

import ectimel.message_broker.EventController;
import ectimel.message_broker.EventListener;
import user_access.UserRegisteredEvent;

@EventController
public class NotificationUserAccessHandlers {
    
    
    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        System.out.println("Sending email to: " + event.email());
    }
    
    
    
}
