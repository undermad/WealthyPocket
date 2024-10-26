package wallet.factories;

import wallet.entities.Owner;
import wallet.entities.Wallet;
import wallet.policies.WalletPolicyData;
import wallet.values.WalletId;

import java.util.List;
import java.util.UUID;

public class StandardWalletFactory implements WalletFactory {
    
    private final MoneyFactory moneyFactory;

    public StandardWalletFactory(MoneyFactory moneyFactory) {
        this.moneyFactory = moneyFactory;
    }

    @Override
    public Wallet createWallet(Owner owner, WalletPolicyData walletPolicyData) {
        var wallet = Wallet.builder()
                .owner(owner)
                .build();
        
        wallet.setId(new WalletId(UUID.randomUUID()));
        
        var money = moneyFactory.createMoney(wallet, walletPolicyData);
        
        wallet.setMoney(List.of(money));
        
        return wallet;
    }
}
