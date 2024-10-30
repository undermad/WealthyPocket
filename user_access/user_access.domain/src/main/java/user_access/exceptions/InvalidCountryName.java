package user_access.exceptions;

import wallet.exceptions.EWalletException;

public class InvalidCountryName extends EWalletException {
    public InvalidCountryName(String message) {
        super(message);
    }
}
