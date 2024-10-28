package wallet.exceptions;

import ectimel.exceptions.EWalletException;

public class NotSufficientBalance extends EWalletException
{
    public NotSufficientBalance(String message)
    {
        super(message);
    }
}
