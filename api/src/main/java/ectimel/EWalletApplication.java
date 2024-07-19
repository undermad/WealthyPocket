package ectimel;

import ectimel.message_broker.MessageBroker;
import ectimel.message_broker.TestEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EWalletApplication implements CommandLineRunner {
	
	private final MessageBroker messageBroker;

    public EWalletApplication(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    public static void main(String[] args) {
		SpringApplication.run(EWalletApplication.class, args);
	}
	
	


	@Override
	public void run(String... args) throws Exception {

		TestEvent testEvent = new TestEvent();
		messageBroker.publish(testEvent);
	}
}
