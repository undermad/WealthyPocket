package user_access.config;

import user_access.factories.AccountFactory;
import user_access.factories.AdminFactory;
import user_access.factories.UserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import user_access.policies.UserPolicy;

import java.util.List;

@Configuration
public class FactoriesConfig {
    
    
    @Bean
    public AccountFactory userFactory(List<UserPolicy> userPolicies) {
        return new UserFactory(userPolicies);
    }
    
    @Bean 
    public AccountFactory adminFactory() {
        return new AdminFactory();
    }
    
}
