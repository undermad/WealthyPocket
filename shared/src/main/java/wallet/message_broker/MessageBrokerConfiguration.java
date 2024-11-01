package wallet.message_broker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class MessageBrokerConfiguration {
    
    @Bean("messageBrokerTaskExecutor")
    public TaskExecutor messageBrokerTaskExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(40);
        executor.setMaxPoolSize(45);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("MessageBroker-Thread-");
        executor.initialize();
        return executor;
    }
}
