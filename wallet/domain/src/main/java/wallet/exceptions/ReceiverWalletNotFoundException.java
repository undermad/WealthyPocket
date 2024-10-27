package wallet.exceptions;

import ectimel.exceptions.EWalletException;
import wallet.values.WalletId;

public class ReceiverWalletNotFoundException extends EWalletException
{
    public ReceiverWalletNotFoundException(WalletId receiver)
    {
        super("Wallet id: " + receiver.id() + " not found");

    }
}
