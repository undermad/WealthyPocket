package ectimel;

import ectimel.message_broker.*;

@EventController
public class TestSubscriber {
    
    
    @EventListener(TestEvent.class)
    public void onTestEvent(TestEvent event) {
        System.out.println("Handling test event " + event.getClass().getSimpleName());
    }


}
