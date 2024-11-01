package wallet.repositories;

import wallet.entities.Owner;
import wallet.values.OwnerId;
import wallet.values.UserId;
import wallet.values.WalletId;

import java.util.Optional;

public interface OwnerRepository {
    
    Optional<Owner> get(OwnerId ownerId);
    Optional<Owner> get(UserId userId);
    
    Optional<Owner> getByWalletId(WalletId walletId);
    void addAsync(Owner owner);
    void updateAsync(Owner owner);
    void deleteAsync(Owner owner);
    
}
