package wallet.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wallet.values.Currency;
import wallet.values.MoneyAmount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WalletAddFundsTest
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
                                .build())

                ))
                .build();
    }

    @Test
    public void new_currency()
    {
        var moneyToAdd = Money.builder()
                .currencyCode(new Currency("PLN"))
                .wallet(null)
                .amount(new MoneyAmount(BigDecimal.valueOf(10.99)))
                .build();

        wallet.addFunds(moneyToAdd);

        assertTrue(wallet.getMoney().stream().anyMatch(money -> money.getCurrencyCode().equals(new Currency("PLN"))));
        assertEquals(2, wallet.getMoney().size());
    }

    @Test
    public void existing_currency()
    {
        var moneyToAdd = Money.builder()
                .currencyCode(new Currency("GBP"))
                .wallet(null)
                .amount(new MoneyAmount(BigDecimal.valueOf(10.99)))
                .build();

        wallet.addFunds(moneyToAdd);

        assertEquals(1, wallet.getMoney().size());
    }

    @Test
    public void null_value()
    {
        assertThrows(NullPointerException.class, () -> wallet.addFunds(null));
    }
}