package wallet.factories;

import wallet.entities.Owner;

import java.util.List;
import java.util.UUID;

public class StandardOwnerFactory implements OwnerFactory {
    
    private WalletFactory walletFactory;

    public StandardOwnerFactory(WalletFactory walletFactory) {
        this.walletFactory = walletFactory;
    }

    @Override
    public Owner createOwner(UUID userId) {
        var owner = Owner.builder()
                .userId(userId)
                .build();
        
        var wallet = walletFactory.createWallet(owner);
        
        owner.setWallets(List.of(wallet));
        
        return owner;
    }
}
