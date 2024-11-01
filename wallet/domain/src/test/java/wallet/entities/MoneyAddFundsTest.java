package wallet.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wallet.exceptions.CurrencyDoesntMatchException;
import wallet.exceptions.NegativeValueException;
import wallet.values.Currency;
import wallet.values.MoneyAmount;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class MoneyAddFundsTest
{

    private Money money;

    @BeforeEach
    public void beforeAllSetup()
    {
        money = Money.builder()
                .currencyCode(new Currency("GBP"))
                .wallet(null)
                .amount(new MoneyAmount(BigDecimal.valueOf(10)))
                .build();
    }


    @Test
    public void null_values()
    {
        assertThrows(NullPointerException.class, () -> money.addFunds(null));
    }

    @Test
    public void negative_values()
    {
        var moneyToAdd = Money.builder()
                .currencyCode(new Currency("GBP"))
                .wallet(null)
                .amount(new MoneyAmount(BigDecimal.valueOf(-10)))
                .build();
        
        assertThrows(NegativeValueException.class, () -> money.addFunds(moneyToAdd));
    }
    
    @Test
    public void positive_values()
    {
        var moneyToAdd = Money.builder()
                .currencyCode(new Currency("GBP"))
                .wallet(null)
                .amount(new MoneyAmount(BigDecimal.valueOf(10.99)))
                .build();

        money.addFunds(moneyToAdd);
        
        assertEquals(20.99, money.getAmount().value().doubleValue());
    }
    
    @Test
    public void different_currencies()
    {
        var moneyToAdd = Money.builder()
                .currencyCode(new Currency("PLN"))
                .wallet(null)
                .amount(new MoneyAmount(BigDecimal.valueOf(10.99)))
                .build();
        
        assertThrows(CurrencyDoesntMatchException.class, () -> money.addFunds(moneyToAdd));
    }

}