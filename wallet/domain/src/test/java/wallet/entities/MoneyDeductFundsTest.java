package wallet.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wallet.exceptions.CurrencyDoesntMatchException;
import wallet.exceptions.NotSufficientBalance;
import wallet.exceptions.NegativeValueException;
import wallet.values.Currency;
import wallet.values.MoneyAmount;
import wallet.values.WalletId;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyDeductFundsTest
{

    private Money money;

    @BeforeEach
    private void setup()
    {
        money = Money.builder()
                .currencyCode(new Currency("GBP"))
                .wallet(Wallet.builder().id(new WalletId(UUID.randomUUID())).build())
                .amount(new MoneyAmount(BigDecimal.valueOf(15)))
                .build();
    }

    @Test
    public void null_values()
    {
        assertThrows(NullPointerException.class, () -> money.deductFunds(null));
    }

    @Test
    public void valid_amount()
    {
        var moneyToDeduct = Money.builder()
                .amount(new MoneyAmount(new BigDecimal("10")))
                .wallet(null)
                .currencyCode(new Currency("GBP"))
                .build();

        money.deductFunds(moneyToDeduct);

        assertEquals(5, money.getAmount().value().doubleValue());
    }

    @Test
    public void invalid_amount()
    {
        var moneyToDeduct = Money.builder()
                .amount(new MoneyAmount(new BigDecimal("-10")))
                .wallet(null)
                .currencyCode(new Currency("GBP"))
                .build();

        assertThrows(NegativeValueException.class, () -> money.deductFunds(moneyToDeduct));
    }
    
    @Test
    public void invalid_currency()
    {
        var moneyToDeduct = Money.builder()
                .amount(new MoneyAmount(new BigDecimal("10")))
                .wallet(null)
                .currencyCode(new Currency("PLN"))
                .build();

        assertThrows(CurrencyDoesntMatchException.class, () -> money.deductFunds(moneyToDeduct));
    }
    
    @Test
    public void insufficient_amount()
    {
        var moneyToDeduct = Money.builder()
                .amount(new MoneyAmount(new BigDecimal("100")))
                .wallet(null)
                .currencyCode(new Currency("GBP"))
                .build();

        assertThrows(NotSufficientBalance.class, () -> money.deductFunds(moneyToDeduct));
    }


}
