package wallet.factories;

import wallet.entities.Money;
import wallet.entities.Wallet;
import wallet.policies.WalletPolicyData;
import wallet.values.WalletId;

public interface MoneyFactory {
    Money createMoney(Wallet wallet, WalletPolicyData walletPolicyData);
}
