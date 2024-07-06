package ectimel.exceptions;

public class PasswordNotValidException extends EWalletException {
    public PasswordNotValidException() {
        super("Invalid value.");
    }
}
