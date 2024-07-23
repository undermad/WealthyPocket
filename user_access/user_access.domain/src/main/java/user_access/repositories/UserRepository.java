package user_access.repositories;

import user_access.entities.User;
import user_access.value_objects.Email;

import java.util.concurrent.CompletableFuture;

public interface UserRepository {
    CompletableFuture<User> getAsync(Email email);
    CompletableFuture<Void> addAsync(User user);
    CompletableFuture<Void> updateAsync(User user);
    CompletableFuture<Void> deleteAsync(User user);
}
