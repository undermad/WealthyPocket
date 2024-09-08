package user_access.exceptions;

import ectimel.exceptions.EWalletException;

public class InvalidCountryName extends EWalletException {
    public InvalidCountryName(String message) {
        super(message);
    }
}
