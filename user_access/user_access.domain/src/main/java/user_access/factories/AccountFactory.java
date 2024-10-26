package user_access.factories;

import user_access.entities.User;

public interface AccountFactory {
    User createUser(UserRegistrationData registrationData);
}
