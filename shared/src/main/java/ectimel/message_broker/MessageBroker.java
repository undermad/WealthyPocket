package ectimel.message_broker;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface MessageBroker {
    
    void publish(Event event) throws ExecutionException, InterruptedException;
    
    void subscribe(Class<? extends Event> event, Subscriber subscriber);
    
}
