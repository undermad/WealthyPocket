package user_access.config;

import user_access.factories.AccountFactory;
import user_access.factories.AdminFactory;
import user_access.factories.UserFactory;
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
