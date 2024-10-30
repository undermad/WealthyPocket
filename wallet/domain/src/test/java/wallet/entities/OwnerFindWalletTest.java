package wallet.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wallet.values.Currency;
import wallet.values.WalletId;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerFindWalletTest
{
    private Owner owner;
    
    @BeforeEach
    public void setup()
    {
        owner = WalletTestingFactory.createOwner();
        owner.addNewWallet(new Currency("GBP"));
    }
    
    @Test
    public void findWallet_validId()
    {
        var walletId = owner.getWallets().getFirst().getId();
        
        var foundWallet = owner.findWallet(walletId);
        
        assertTrue(foundWallet.isPresent());
        assertEquals(foundWallet.get().getId().id().toString(), walletId.id().toString());
    }
    
    @Test
    public void findWallet_invalidId()
    {
        var walletId = new WalletId(UUID.randomUUID());
        
        var foundWallet = owner.findWallet(walletId);

        assertTrue(foundWallet.isEmpty());
    }
    
    @Test
    public void findWallet_nullValues()
    {
        assertThrows(NullPointerException.class, () -> owner.findWallet(null));
    }
    
}
