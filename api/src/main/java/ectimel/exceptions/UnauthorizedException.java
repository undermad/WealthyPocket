package ectimel.exceptions;

public class UnauthorizedException extends EWalletException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super("Bad credentials.");
    }
}
