package ectimel.message_broker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryMessageBroker implements MessageBroker {

    private final Map<Class<? extends Event>, List<Subscriber>> eventToSubscribers = new ConcurrentHashMap<>();

    @Override
    public void publish(Event event) {

        long startTime = System.nanoTime();
        
        var subscribers = eventToSubscribers.get(event.getClass());
        if (subscribers != null) {
            subscribers.forEach(subscriber -> subscriber.update(event));
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double durationInMilliseconds = duration / 1_000_000.0;
        System.out.println("Execution time in milliseconds: " + durationInMilliseconds);
    }

    @Override
    public void subscribe(Class<? extends Event> clazz, Subscriber subscriber) {
        eventToSubscribers.computeIfAbsent(clazz, k -> new ArrayList<>()).add(subscriber);
    }
}
