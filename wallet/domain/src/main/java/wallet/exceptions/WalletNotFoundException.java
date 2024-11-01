package wallet.exceptions;

import wallet.values.WalletId;

public class WalletNotFoundException extends EWalletException
{
    public WalletNotFoundException(WalletId walletId)
    {
        super("Wallet id: " + walletId.id() + " not found in owner");

    }
}
