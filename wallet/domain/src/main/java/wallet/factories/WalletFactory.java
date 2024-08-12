package wallet.factories;

import wallet.entities.Owner;
import wallet.entities.Wallet;
import wallet.values.OwnerId;

public interface WalletFactory {
    Wallet createWallet(Owner owner);
}
