package api;

import ectimel.message_broker.MessageBroker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"wallet", "user_access", "ectimel", "api", "notification"})
@EnableScheduling
public class EWalletApplication {

    public static void main(String[] args) {
		SpringApplication.run(EWalletApplication.class, args);
	}
	
}
