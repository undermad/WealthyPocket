package wallet.persistence.repositories.write;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import wallet.entities.Owner;
import wallet.repositories.OwnerRepository;
import wallet.values.OwnerId;
import wallet.values.UserId;

@Repository
public class PostgresOwnerRepository implements OwnerRepository {
    
    @PersistenceContext(unitName = "puWriteWallet")
    private EntityManager entityManager;
    
    @Override
    public Owner get(OwnerId id) {
        var query = entityManager.createQuery("SELECT o FROM Owner o WHERE o.id = :id", Owner.class);
        query.setParameter("id", id);

        var result = query.getResultList();
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public Owner get(UserId userId) {
        var query = entityManager.createQuery("SELECT o FROM Owner o WHERE o.userId = :userId", Owner.class);
        query.setParameter("userId", userId);
        
        var result = query.getResultList();
        return result.isEmpty() ? null : result.getFirst(); 
        
    }

    @Override
    public void addAsync(Owner owner) {
        entityManager.persist(owner);
    }

    @Override
    public void updateAsync(Owner owner) {

    }

    @Override
    public void deleteAsync(Owner owner) {

    }
}
