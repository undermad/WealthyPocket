package wallet.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wallet.exceptions.CurrencyDoesntMatchException;
import wallet.values.Currency;
import wallet.values.MoneyAmount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WalletDeductFundsTest
{

    private Wallet wallet;

    @BeforeEach
    public void setup()
    {
        wallet = Wallet.builder()
                .owner(null)
                .money(new ArrayList<>(List.of(
                        Money.builder()
                                .amount(new MoneyAmount(BigDecimal.valueOf(10)))
                                .currencyCode(new Currency("GBP"))
                                .wallet(wallet)
                                .build())))
                .build();
    }

    @Test
    public void null_values()
    {
        assertThrows(NullPointerException.class, () -> wallet.deductFunds(null));
    }
    
    @Test
    public void existing_currency()
    {
        var moneyToAdd = Money.builder()
                .currencyCode(new Currency("GBP"))
                .wallet(null)
                .amount(new MoneyAmount(BigDecimal.valueOf(5)))
                .build();

        wallet.deductFunds(moneyToAdd);

        assertEquals(5, wallet.getMoney().getFirst().getAmount().value().doubleValue());
    }

    @Test
    public void not_existing_currency()
    {
        var moneyToAdd = Money.builder()
                .currencyCode(new Currency("PLN"))
                .wallet(null)
                .amount(new MoneyAmount(BigDecimal.valueOf(5)))
                .build();
        
        assertThrows(CurrencyDoesntMatchException.class, () -> wallet.deductFunds(moneyToAdd));
    }
}
