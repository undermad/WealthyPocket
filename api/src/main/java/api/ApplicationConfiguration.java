package api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = {"wallet", "user_access", "wallet", "api", "notification"})
@EnableScheduling
public class ApplicationConfiguration
{
}
