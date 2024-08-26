package ectimel.message_broker;


import ectimel.inbox.InboxRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;


@Component
public class EventListenerBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private final MessageBroker messageBroker;
    private ApplicationContext applicationContext;

    public EventListenerBeanPostProcessor(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(EventController.class)) {

            String inboxRepositoryBean = bean.getClass().getAnnotation(EventController.class).inboxRepositoryBean();
            var repository = applicationContext.getBean(inboxRepositoryBean);

            if (repository instanceof InboxRepository<?>) {
                
                var methods = bean.getClass().getMethods();
                for (var method : methods) {
                    var annotation = method.getAnnotation(EventListener.class);
                    if (annotation == null) continue;

                    var parameter = method.getParameters()[0].getType();
                    if (Event.class.isAssignableFrom(parameter)) {
                        messageBroker.subscribe((Class<? extends Event>) parameter, (InboxRepository<InboxMessage>) repository);
                    }
                }
            }
        }

        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
