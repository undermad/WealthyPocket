package wallet.factories;

import wallet.entities.Money;
import wallet.entities.Wallet;
import wallet.values.MoneyAmount;
import wallet.values.MoneyCurrency;

import java.math.BigInteger;

public class StandardMoneyFactory implements MoneyFactory {
    
    @Override
    public Money createMoney(Wallet wallet) {

        
        return Money.builder()
                .wallet(wallet)
                .amount(new MoneyAmount(new BigInteger("0")))
                .currencyCode(new MoneyCurrency("GBP"))
                .build();
    }
}
