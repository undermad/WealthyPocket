package user_access.exceptions;

import wallet.exceptions.EWalletException;

public class UnauthorizedException extends EWalletException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super("Bad credentials.");
    }
}
