package ectimel;

import ectimel.message_broker.MessageBroker;
import ectimel.message_broker.Subscriber;
import ectimel.message_broker.TestEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TestSubscriber implements Subscriber {
    
    private final MessageBroker messageBroker;

    public TestSubscriber(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @PostConstruct
    public void subscribe() {
        messageBroker.subscribe(TestEvent.class, this);
    } 
    
    
    @Override
    public void update() {
        System.out.println("Updating");
    }
}
