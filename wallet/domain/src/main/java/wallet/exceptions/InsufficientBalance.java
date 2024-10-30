package wallet.exceptions;

public class InsufficientBalance extends EWalletException
{
    public InsufficientBalance(String message)
    {
        super(message);
    }
}
