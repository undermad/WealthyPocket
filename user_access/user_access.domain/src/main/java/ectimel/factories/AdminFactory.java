package ectimel.factories;

import ectimel.entities.Role;
import ectimel.entities.User;
import ectimel.value_objects.Email;
import ectimel.value_objects.Password;
import ectimel.value_objects.UserId;

import java.util.Set;
import java.util.UUID;

public class AdminFactory implements AccountFactory {
    
    @Override
    public User createUser(Email email, Password password) {
        Role role = Role.createAdminRole();
        UserId userId = new UserId(UUID.randomUUID());
        return new User(userId, email, password, Set.of(role));
    }
    
}
