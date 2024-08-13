package ectimel.message_broker;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class InMemoryMessageBroker implements MessageBroker {

    private final Map<Class<? extends Event>, List<Subscriber>> eventToSubscribers = new ConcurrentHashMap<>();

    private final TaskExecutor taskExecutor;

    public InMemoryMessageBroker(@Qualifier("messageBrokerTaskExecutor") TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void publish(Event event) throws ExecutionException, InterruptedException {

        long startTime = System.nanoTime();

        var subscribers = eventToSubscribers.get(event.getClass());
        if (subscribers != null) {
            List<CompletableFuture<Void>> futures = new ArrayList<>();

            for (var subscriber : subscribers) {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> subscriber.update(event), taskExecutor);
                futures.add(future);
            }

            CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

            allOf.get();
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
