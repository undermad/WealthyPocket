package ectimel.message_broker;

import org.springframework.scheduling.annotation.Async;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventListener {
    Class<? extends Event> value();
}
