package wallet.factories;

import wallet.entities.Money;
import wallet.entities.Wallet;
import wallet.values.MoneyAmount;
import wallet.values.MoneyCurrency;
import wallet.values.MoneyId;

import java.math.BigInteger;
import java.util.UUID;

public class StandardMoneyFactory implements MoneyFactory {
    
    @Override
    public Money createMoney(Wallet wallet) {

        var money = Money.builder()
                .wallet(wallet)
                .amount(new MoneyAmount(new BigInteger("0")))
                .currencyCode(new MoneyCurrency("GBP"))
                .build();
        
        money.setId(new MoneyId(UUID.randomUUID()));
        
        return money;
    }
}
