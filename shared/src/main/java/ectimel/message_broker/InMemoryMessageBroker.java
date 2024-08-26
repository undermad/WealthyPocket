package ectimel.message_broker;

import ectimel.inbox.InboxRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.security.Key;
import java.util.*;
import java.util.concurrent.*;

@Component
public class InMemoryMessageBroker implements MessageBroker {

    private final Map<Class<? extends Event>, List<InboxRepository<InboxMessage>>> eventToSubscribers = new ConcurrentHashMap<>();

    private final TaskExecutor taskExecutor;

    public InMemoryMessageBroker(@Qualifier("messageBrokerTaskExecutor") TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void publish(Event event) throws ExecutionException, InterruptedException {

        long startTime = System.nanoTime();

        var subscribingRepositories = eventToSubscribers.get(event.getClass());

        if (subscribingRepositories != null) {
            List<CompletableFuture<Void>> futures = new ArrayList<>();

            for (var subscriber : subscribingRepositories) {

                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    var idx = subscribingRepositories.indexOf(subscriber);
                    var repository = subscribingRepositories.get(idx);
                    var message = repository.getMessageByEventId(event.getId());
                    if (message != null) return;
                    repository.saveMessage(event);
                }, taskExecutor);

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
    public void subscribe(Class<? extends Event> clazz, InboxRepository<InboxMessage> repository) {
        eventToSubscribers.computeIfAbsent(clazz, k -> new ArrayList<>());
        eventToSubscribers.get(clazz).add(repository);
    }
}
