package wallet.persistence.repositories.write;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import wallet.entities.Owner;
import wallet.repositories.OwnerRepository;
import wallet.values.OwnerId;
import wallet.values.UserId;
import wallet.values.WalletId;

import java.util.Optional;

@Repository
public class PostgresOwnerRepository implements OwnerRepository {
    
    @PersistenceContext(unitName = "puWriteWallet")
    private EntityManager entityManager;
    
    @Override
    public Optional<Owner> get(OwnerId id) {
        var query = entityManager.createQuery("SELECT o FROM Owner o WHERE o.id = :id", Owner.class);
        query.setParameter("id", id);

        var result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.getFirst());
    }

    @Override
    public Optional<Owner> get(UserId userId) {
        var query = entityManager.createQuery("SELECT o FROM Owner o WHERE o.userId = :userId", Owner.class);
        query.setParameter("userId", userId);
        
        return query.getResultStream().findFirst();
    }

    @Override
    public Optional<Owner> getByWalletId(WalletId walletId)
    {
        var query = entityManager.createQuery("""
                SELECT o FROM Owner o JOIN Wallet w ON o = w.owner
                WHERE w.id = :walletId
                """, Owner.class);
        
        query.setParameter("walletId", walletId);
        
        var result = query.getResultStream();
        return result.findFirst();
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
