package ectimel.config;

import ectimel.factories.AccountFactory;
import ectimel.factories.AdminFactory;
import ectimel.factories.UserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoriesConfig {
    
    
    @Bean
    public AccountFactory userFactory() {
        return new UserFactory();
    }
    
    @Bean 
    public AccountFactory adminFactory() {
        return new AdminFactory();
    }
    
}
