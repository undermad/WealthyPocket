package user_access.factories;

import user_access.entities.User;
import user_access.policies.UserRegistrationData;

public class AdminFactory implements AccountFactory {
    
    @Override
    public User createUser(UserRegistrationData userRegistrationData) {
        return null;
    }
    
}
