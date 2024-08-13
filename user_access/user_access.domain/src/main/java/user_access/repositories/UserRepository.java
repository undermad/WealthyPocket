package user_access.repositories;

import user_access.entities.User;
import user_access.value_objects.Email;

import java.util.concurrent.CompletableFuture;

public interface UserRepository {
    User getAsync(Email email);
    
    void addAsync(User user);
    void updateAsync(User user);
    void deleteAsync(User user);
}
