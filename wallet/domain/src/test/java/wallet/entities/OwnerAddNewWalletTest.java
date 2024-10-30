package wallet.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wallet.values.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OwnerAddNewWalletTest
{
    private Owner owner;

    @BeforeEach
    public void setup()
    {
        owner = WalletTestingFactory.createOwner();
    }
    
    @Test
    public void addNewWallet_nullValues()
    {
        assertThrows(NullPointerException.class, () -> owner.addNewWallet(null));
    }

    @Test
    public void addNewWallet_GBPCurrency()
    {
        var currency = new Currency("GBP");

        owner.addNewWallet(currency);

        assertEquals(1, owner.getWallets().size());
        assertEquals(0, owner.getWallets().getFirst().getMoney().getFirst().getAmount().value().doubleValue());
        assertEquals("GBP", owner.getWallets().getFirst().getMoney().getFirst().getCurrencyCode().value());
    }

    @Test
    public void addNewWallet_ShouldBindEachOther()
    {
        var currency = new Currency("PLN");

        owner.addNewWallet(currency);

        assertEquals(owner.getUserId().id().toString(), owner.getWallets().getFirst().getOwner().getUserId().id().toString());
        assertEquals(owner.getWallets().getFirst().getId().id().toString(),
                owner.getWallets().getFirst().getMoney().getFirst().getWallet().getId().id().toString());
    }


}
