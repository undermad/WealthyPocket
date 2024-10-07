package api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = {"wallet", "user_access", "ectimel", "api", "notification"})
@EnableScheduling
public class ApplicationConfiguration
{
}
