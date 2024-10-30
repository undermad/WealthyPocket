package wallet.factories;

import wallet.entities.Money;
import wallet.entities.Owner;
import wallet.entities.Wallet;
import wallet.policies.WalletPolicyData;
import wallet.values.Currency;
import wallet.values.OwnerId;
import wallet.values.WalletId;

import java.util.ArrayList;
import java.util.UUID;

public interface WalletFactory {
    Wallet createWallet(Owner owner, WalletPolicyData walletPolicyData);

    static Wallet createEmptyWallet(Owner owner, Currency currency)
    {
        var listOfMoney = new ArrayList<Money>();
        var wallet = Wallet.builder()
                .id(new WalletId(UUID.randomUUID()))
                .owner(owner)
                .money(listOfMoney)
                .build();
        listOfMoney.add(MoneyFactory.createEmptyMoney(wallet, currency));
        return wallet;
    }
}
