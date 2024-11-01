package notification.persistence.inbox;

import wallet.inbox.InboxRepository;
import wallet.message_broker.EventListener;
import wallet.outbox.Poller;
import wallet.utils.JsonMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component("notificationInboxPoller")
public class InboxPoller implements Poller, ApplicationContextAware {


    private final InboxRepository<NotificationInboxMessage> inboxRepository;
    private ApplicationContext applicationContext;

    public InboxPoller(@Qualifier("notificationInboxRepository") InboxRepository<NotificationInboxMessage> inboxRepository) {
        this.inboxRepository = inboxRepository;
    }

    @Scheduled(fixedRate = 5000L)
    public void handleInboxMessagesTask() throws InvocationTargetException, IllegalAccessException {
        poll();
    }

    @Override
    public void poll() throws InvocationTargetException, IllegalAccessException {

        var messages = inboxRepository.getAllMessages();

        var clazzToMessages = new HashMap<Class<?>, List<NotificationInboxMessage>>();

        messages.forEach(message -> {
            try {
                Class<?> clazz = Class.forName(message.getEventType());
                clazzToMessages.computeIfAbsent(clazz, aClass -> new ArrayList<>()).add(message);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });


        var eventController = applicationContext.getBean("notificationEventHandlers");
        var methods = eventController.getClass().getMethods();

        for (var method : methods) {

            var annotation = method.getAnnotation(EventListener.class);
            if (annotation == null) continue;
            
            var parameters = method.getParameters();
            var firstParameter = parameters[0];
            if (firstParameter == null) continue;

            var eventParameterType = firstParameter.getType();
            var messageToProcess = clazzToMessages.get(eventParameterType);
            if (messageToProcess == null || messageToProcess.isEmpty()) continue;


            for (var msg : messageToProcess) {
                var event = JsonMapper.fromJson(msg.getPayload(), eventParameterType);
                method.invoke(eventController, event);
                msg.setProcessed(true);
                msg.setProcessedAt(Instant.now());
                inboxRepository.updateMessage(msg);
            }


        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
