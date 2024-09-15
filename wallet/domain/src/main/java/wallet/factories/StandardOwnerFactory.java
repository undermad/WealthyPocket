package wallet.factories;

import wallet.entities.Owner;
import wallet.policies.WalletPolicyData;
import wallet.values.OwnerId;

import java.util.List;
import java.util.UUID;

public class StandardOwnerFactory implements OwnerFactory {
    
    private WalletFactory walletFactory;

    public StandardOwnerFactory(WalletFactory walletFactory) {
        this.walletFactory = walletFactory;
    }

    @Override
    public Owner createOwner(WalletPolicyData walletPolicyData) {
        var owner = Owner.builder()
                .userId(walletPolicyData.userId())
                .build();
        
        owner.setId(new OwnerId(UUID.randomUUID()));
        
        var wallet = walletFactory.createWallet(owner, walletPolicyData);
        
        owner.setWallets(List.of(wallet));
        
        return owner;
    }
    
}
