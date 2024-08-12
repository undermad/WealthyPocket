package wallet.exceptions;

import ectimel.exceptions.EWalletException;
import wallet.entities.Owner;
import wallet.entities.Wallet;

public class InvalidCurrency extends EWalletException {
    public InvalidCurrency(String s) {
        super(s);
    }
}
