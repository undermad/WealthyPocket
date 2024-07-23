package user_access.exceptions;

import ectimel.exceptions.EWalletException;

public class UnauthorizedException extends EWalletException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super("Bad credentials.");
    }
}
