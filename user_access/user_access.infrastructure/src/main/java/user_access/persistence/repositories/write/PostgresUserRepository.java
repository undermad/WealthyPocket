package user_access.persistence.repositories.write;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import user_access.entities.User;
import user_access.repositories.UserRepository;
import user_access.value_objects.Email;
import user_access.value_objects.UserId;

import java.util.UUID;

@Repository
public class PostgresUserRepository implements UserRepository {
    
    
    @PersistenceContext(unitName = "puWriteUserAccess")
    private EntityManager entityManager;


    @Override
    public User get(Email email) {
        
        var query = entityManager.createQuery("""
                SELECT u FROM User u WHERE u.email = :email
                """, User.class);
        
        query.setParameter("email", email);
        
        var users = query.getResultList();

        return users.isEmpty() ? null : users.getFirst();
    }

    @Override
    public User get(UserId userId) {
        
        var query = entityManager.createQuery("""
                        SELECT u FROM User u WHERE u.id = :userId
                        """, User.class);
        
        query.setParameter("userId", userId);
        
        var users = query.getResultList();
        
        return users.isEmpty() ? null : users.getFirst();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
    }
}
