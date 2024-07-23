package user_access.persistence.repositories.write;

import user_access.entities.User;
import user_access.repositories.UserRepository;
import user_access.value_objects.Email;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Async
@Repository
public class PostgresUserRepository implements UserRepository {
    
    
    @PersistenceContext(unitName = "puWriteUserAccess")
    private EntityManager entityManager;


    @Override
    public CompletableFuture<User> getAsync(Email email) {
        
        TypedQuery<User> query = entityManager
                .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        
        query.setParameter("email", email);
        
        var users = query.getResultList();
        var user = users.isEmpty() ? null : users.getFirst();
        
        return CompletableFuture.completedFuture(user);
    }

    @Override
    public CompletableFuture<Void> addAsync(User user) {
        entityManager.persist(user);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> updateAsync(User user) {
        return null;
    }

    @Override
    public CompletableFuture<Void> deleteAsync(User user) {
        return null;
    }
}
