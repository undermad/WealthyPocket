package user_access.exceptions;

import wallet.exceptions.EWalletException;

public class InvalidBornDate extends EWalletException {
    public InvalidBornDate(String message) {
        super(message);
    }
}
