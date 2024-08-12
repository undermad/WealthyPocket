package wallet.factories;

import wallet.entities.Owner;
import wallet.entities.Wallet;

import java.util.List;

public class StandardWalletFactory implements WalletFactory {
    
    private MoneyFactory moneyFactory;

    public StandardWalletFactory(MoneyFactory moneyFactory) {
        this.moneyFactory = moneyFactory;
    }

    @Override
    public Wallet createWallet(Owner owner) {
        var wallet = Wallet.builder()
                .owner(owner)
                .build();
        
        var money = moneyFactory.createMoney(wallet);
        
        wallet.setMoney(List.of(money));
        
        return wallet;
    }
}
