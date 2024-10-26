package user_access.factories;

import user_access.entities.User;

public class AdminFactory implements AccountFactory {
    
    @Override
    public User createUser(UserRegistrationData userRegistrationData) {
        return null;
    }
    
}
