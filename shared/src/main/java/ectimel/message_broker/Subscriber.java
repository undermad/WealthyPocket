package ectimel.message_broker;

import org.springframework.scheduling.annotation.Async;

@FunctionalInterface
public interface Subscriber {
    
    @Async
    void update(Event event);
}
