package ectimel.message_broker;

import org.springframework.scheduling.annotation.Async;

@FunctionalInterface
public interface Subscriber {
    
    void update(Event event);
}
