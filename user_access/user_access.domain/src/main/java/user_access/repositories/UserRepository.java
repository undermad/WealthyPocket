package user_access.repositories;

import user_access.entities.User;
import user_access.value_objects.Email;
import user_access.value_objects.UserId;

import java.util.UUID;

public interface UserRepository {
    User get(Email email);
    User get(UserId userId);
    
    void add(User user);
    void update(User user);
    void delete(User user);
}
