package user_access.persistence.repositories.write;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import user_access.entities.User;
import user_access.repositories.UserRepository;
import user_access.value_objects.Email;

@Repository
public class PostgresUserRepository implements UserRepository {
    
    
    @PersistenceContext(unitName = "puWriteUserAccess")
    private EntityManager entityManager;


    @Override
    public User getAsync(Email email) {
        var query = entityManager
                .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        
        query.setParameter("email", email);
        
        var users = query.getResultList();

        return users.isEmpty() ? null : users.getFirst();
    }

    @Override
    public void addAsync(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateAsync(User user) {
    }

    @Override
    public void deleteAsync(User user) {
    }
}
