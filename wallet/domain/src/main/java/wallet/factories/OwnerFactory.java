package wallet.factories;

import wallet.entities.Owner;

import java.util.UUID;

public interface OwnerFactory {
    Owner createOwner(UUID userId);
}
