package user_access.factories;

import user_access.entities.User;
import user_access.policies.UserRegistrationData;
import user_access.value_objects.Email;
import user_access.value_objects.Password;

public interface AccountFactory {
    User createUser(UserRegistrationData registrationData);
}
