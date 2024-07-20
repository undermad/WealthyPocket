package ectimel;

import ectimel.message_broker.*;

@EventController
public class TestSubscriber {

    @EventListener(TestEvent.class)
    public void onTestEvent(TestEvent event) {
        System.out.println(Thread.currentThread().getName());
        event.printMessage();
        // Simulate some delay
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished handling TestEvent");
    }


}
