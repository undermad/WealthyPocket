package user_access.exceptions;

import wallet.exceptions.EWalletException;

public class CountryNotAllowed extends EWalletException {
    public CountryNotAllowed(String message) {
        super(message);
    }
}
