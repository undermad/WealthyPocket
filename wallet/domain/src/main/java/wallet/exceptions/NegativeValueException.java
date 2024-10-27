package wallet.exceptions;

import ectimel.exceptions.EWalletException;

public class NegativeValueException extends EWalletException
{ 
    public NegativeValueException(String message)
    {
        super(message);
    }
}
