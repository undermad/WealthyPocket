package ectimel.cqrs.queries;


import ectimel.cqrs.commands.Handler;
import org.springframework.scheduling.annotation.Async;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Handler
@Async
public @interface AsyncHandler{
}
