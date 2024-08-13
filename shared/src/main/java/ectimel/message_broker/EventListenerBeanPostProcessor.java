package ectimel.message_broker;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;


@Component
public class EventListenerBeanPostProcessor implements BeanPostProcessor {

    private final MessageBroker messageBroker;

    public EventListenerBeanPostProcessor(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(EventController.class)) {
            var methods = bean.getClass().getMethods();
            for (var method : methods) {
                var annotation = method.getAnnotation(EventListener.class);
                if (annotation == null) continue;

                var parameter = method.getParameters()[0].getType();
                if (Event.class.isAssignableFrom(parameter)) {
                    method.setAccessible(true);
                    messageBroker.subscribe((Class<? extends Event>) parameter,
                            event -> {
                                try {
                                    method.invoke(bean, event);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                }
            }
        }

        return bean;
    }
}
