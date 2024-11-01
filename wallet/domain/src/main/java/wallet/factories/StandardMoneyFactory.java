package wallet.factories;

import wallet.entities.Money;
import wallet.entities.Wallet;
import wallet.policies.WalletPolicyData;
import wallet.values.MoneyAmount;
import wallet.values.Currency;
import wallet.values.MoneyId;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

public class StandardMoneyFactory implements MoneyFactory {

    @Override
    public Money createMoney(Wallet wallet, WalletPolicyData walletPolicyData) {


        var currencyCode = java.util.Currency.getInstance(Arrays.stream(Locale.getAvailableLocales())
                .filter(locale -> locale.getCountry().equalsIgnoreCase(walletPolicyData.country()))
                .toList()
                .getFirst())
                .getCurrencyCode();

        var money = Money.builder()
                .wallet(wallet)
                .amount(new MoneyAmount(new BigDecimal("0.00")))
                .currencyCode(new Currency(currencyCode))
                .build();

        money.setId(new MoneyId(UUID.randomUUID()));

        return money;
    }
}
