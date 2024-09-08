package wallet.factories;

import wallet.entities.Owner;
import wallet.policies.WalletPolicyData;

import java.util.UUID;

public interface OwnerFactory {
    Owner createOwner(WalletPolicyData userId);
}
