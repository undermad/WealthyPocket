package user_access.factories;

import user_access.entities.Role;
import user_access.entities.User;
import user_access.value_objects.Email;
import user_access.value_objects.Password;
import user_access.value_objects.UserId;

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
