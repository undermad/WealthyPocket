package ectimel.factories;

import ectimel.entities.User;
import ectimel.value_objects.Email;
import ectimel.value_objects.Password;

public interface AccountFactory {
    User createUser(Email email, Password password);
}
