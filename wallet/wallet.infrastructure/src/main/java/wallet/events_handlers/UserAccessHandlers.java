package wallet.events_handlers;

import ectimel.message_broker.EventController;
import ectimel.message_broker.EventListener;
import user_access.UserRegisteredEvent;

@EventController
public class UserAccessHandlers {


    @EventListener()
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        System.out.println("bbb");
    }
    
}
