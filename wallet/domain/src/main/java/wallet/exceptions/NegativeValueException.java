package wallet.exceptions;

public class NegativeValueException extends EWalletException
{ 
    public NegativeValueException(String message)
    {
        super(message);
    }
}
