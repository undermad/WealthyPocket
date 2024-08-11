package wallet.exceptions;

import ectimel.exceptions.EWalletException;

public class InvalidCurrency extends EWalletException {
    public InvalidCurrency(String s) {
        super(s);
    }
}
