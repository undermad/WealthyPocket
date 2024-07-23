package ectimel.message_broker;


import ectimel.outbox.OutboxRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;


@Component
public class EventListenerBeanPostProcessor implements BeanPostProcessor {

    private final MessageBroker messageBroker;
    private final TaskExecutor taskExecutor;


    public EventListenerBeanPostProcessor(MessageBroker messageBroker, @Qualifier("messageBrokerTaskExecutor") TaskExecutor taskExecutor) {
        this.messageBroker = messageBroker;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(EventController.class)) {
            var methods = bean.getClass().getMethods();
            for (var method : methods) {
                var annotation = method.getAnnotation(EventListener.class);
                if (annotation == null) continue;
                method.setAccessible(true);
                messageBroker.subscribe(annotation.value(), event -> {
                    System.out.println(Thread.currentThread().getName());
                    taskExecutor.execute(() -> {
                        try {
                            method.invoke(bean, event);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    System.out.println("after invoke method should show before sleep");
                });
            }
        }

        return bean;
    }
}
