package wallet.entities;

import ectimel.exceptions.NullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wallet.exceptions.ReceiverWalletNotFoundException;
import wallet.values.Currency;
import wallet.values.MoneyAmount;
import wallet.values.UserId;
import wallet.values.WalletId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OwnerAddFoundsTest
{

    private Owner owner;

    @BeforeEach
    public void setup()
    {

        var walletUUID = UUID.nameUUIDFromBytes("wallet".getBytes());

        owner = Owner.builder()
                .userId(new UserId(UUID.nameUUIDFromBytes("user".getBytes())))
                .wallets(new ArrayList<>(List.of(
                        Wallet.builder()
                                .id(new WalletId(walletUUID))
                                .owner(owner)
                                .money(new ArrayList<>(List.of(
                                        Money.builder()
                                                .currencyCode(new Currency("GBP"))
                                                .wallet(null)
                                                .amount(new MoneyAmount(BigDecimal.valueOf(10.99)))
                                                .build()
                                )))
                                .build()
                )))
                .build();
    }


    @Test
    public void null_values()
    {
        assertThrows(NullException.class, () -> owner.addFounds(new WalletId(UUID.nameUUIDFromBytes("abc".getBytes())), null));
        assertThrows(NullException.class, () -> owner.addFounds(null, new Money()));
        assertThrows(NullException.class, () -> owner.addFounds(null, null));
    }

    @Test
    public void receiver_not_found()
    {
        var walletId = new WalletId(UUID.randomUUID());
        var moneyToAdd = new Money();

        assertThrows(ReceiverWalletNotFoundException.class, () -> owner.addFounds(walletId, moneyToAdd));
    }

    @Test
    public void founds_added()
    {
        var walletUUID = new WalletId(UUID.nameUUIDFromBytes("wallet".getBytes()));
        var moneyToAdd = Money.builder()
                .wallet(null)
                .currencyCode(new Currency("GBP"))
                .amount(new MoneyAmount(BigDecimal.valueOf(10)))
                .build();

        owner.addFounds(walletUUID, moneyToAdd);

        var wallet = owner.getWallets().stream()
                .filter(w -> w.getId().equals(walletUUID))
                .findFirst();
        
        assertTrue(wallet.isPresent());
        assertEquals(20.99, wallet.get().getMoney().getFirst()
                .getAmount().value().doubleValue());
    }

}