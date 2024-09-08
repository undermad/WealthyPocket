package user_access.exceptions;

import ectimel.exceptions.EWalletException;

public class CountryNotAllowed extends EWalletException {
    public CountryNotAllowed(String message) {
        super(message);
    }
}
