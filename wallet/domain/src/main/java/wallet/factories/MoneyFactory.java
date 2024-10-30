package wallet.factories;

import wallet.entities.Money;
import wallet.entities.Wallet;
import wallet.policies.WalletPolicyData;
import wallet.values.Currency;
import wallet.values.MoneyAmount;
import wallet.values.MoneyId;
import wallet.values.WalletId;

import java.math.BigDecimal;
import java.util.UUID;

public interface MoneyFactory {
    Money createMoney(Wallet wallet, WalletPolicyData walletPolicyData);

    static Money createEmptyMoney(Wallet wallet, Currency currency)
    {
        return Money.builder()
                .id(new MoneyId(UUID.randomUUID()))
                .wallet(wallet)
                .amount(new MoneyAmount(BigDecimal.valueOf(0D)))
                .currencyCode(currency)
                .build();
    }

    static Money createTransferableMoney(Double amount, Currency currency)
    {
        return Money.builder()
                .currencyCode(currency)
                .amount(new MoneyAmount(BigDecimal.valueOf(amount)))
                .build();
    }
}
