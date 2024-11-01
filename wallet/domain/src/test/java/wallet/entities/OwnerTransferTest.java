package wallet.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wallet.exceptions.InsufficientBalance;
import wallet.factories.MoneyFactory;
import wallet.values.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OwnerTransferTest
{
    private Owner owner;
    private Owner receiver;

    @BeforeEach
    public void setup()
    {
        owner = WalletTestingFactory.createOwner();
        owner.addNewWallet(new Currency("GBP"));
        var walletId = owner.getWallets().getFirst().getId();
        owner.deposit(walletId, MoneyFactory.createTransferableMoney(100D, new Currency("GBP")));

        receiver = WalletTestingFactory.createOwner();
        receiver.addNewWallet(new Currency("GBP"));
        var receivingWallet = receiver.getWallets().getFirst();
        receiver.deposit(receivingWallet.getId(), MoneyFactory.createTransferableMoney(20D, new Currency("GBP")));
    }

    @Test
    public void valid_transfer()
    {
        var sendingWallet = owner.getWallets().getFirst();
        var receivingWallet = receiver.getWallets().getFirst();
        var moneyToTransfer = MoneyFactory.createTransferableMoney(25D, new Currency("GBP"));
        
        owner.transfer(sendingWallet, receivingWallet, moneyToTransfer);

        assertEquals(75, sendingWallet.getMoney().getFirst().getAmount().value().doubleValue());
        assertEquals(45, receivingWallet.getMoney().getFirst().getAmount().value().doubleValue());
    }
    
    @Test
    public void invalid_transfer_missing_funds()
    {
        var sendingWallet = owner.getWallets().getFirst();
        var receivingWallet = receiver.getWallets().getFirst();
        var moneyToTransfer = MoneyFactory.createTransferableMoney(999D, new Currency("GBP"));
        
        assertThrows(InsufficientBalance.class, () -> owner.transfer(sendingWallet, receivingWallet, moneyToTransfer));
    }


}
