package api;

import ectimel.message_broker.MessageBroker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"wallet", "user_access", "ectimel", "api"})
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
//		
//		TestEvent anotherEvent = new TestEvent("Second message");
//		messageBroker.publish(anotherEvent);
	}
}