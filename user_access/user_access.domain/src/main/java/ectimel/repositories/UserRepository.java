package ectimel.repositories;

import ectimel.entities.User;
import ectimel.value_objects.Email;

import java.util.concurrent.CompletableFuture;

public interface UserRepository {
    CompletableFuture<User> getAsync(Email email);
    CompletableFuture<Void> addAsync(User user);
    CompletableFuture<Void> updateAsync(User user);
    CompletableFuture<Void> deleteAsync(User user);
}
