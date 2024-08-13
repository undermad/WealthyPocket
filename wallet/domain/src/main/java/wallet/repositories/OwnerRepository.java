package wallet.repositories;

import wallet.entities.Owner;
import wallet.values.OwnerId;
import wallet.values.UserId;

public interface OwnerRepository {
    
    Owner get(OwnerId ownerId);
    Owner get(UserId userId);
    void addAsync(Owner owner);
    void updateAsync(Owner owner);
    void deleteAsync(Owner owner);
    
}
