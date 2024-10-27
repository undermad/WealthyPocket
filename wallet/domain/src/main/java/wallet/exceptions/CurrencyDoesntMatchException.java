package wallet.exceptions;

import ectimel.exceptions.EWalletException;

public class CurrencyDoesntMatchException extends EWalletException
{
    public CurrencyDoesntMatchException(String message)
    {
        super(message);
    }
}
