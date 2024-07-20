package ectimel.message_broker;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


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

                messageBroker.subscribe(annotation.value(), event -> {
                    try {
                        method.invoke(bean, event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        return bean;
    }
}
