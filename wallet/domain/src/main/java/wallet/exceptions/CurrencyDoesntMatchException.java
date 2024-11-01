package wallet.exceptions;

public class CurrencyDoesntMatchException extends EWalletException
{
    public CurrencyDoesntMatchException(String message)
    {
        super(message);
    }
}
