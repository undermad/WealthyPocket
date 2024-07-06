package ectimel.exceptions;

public class NullIdException extends EWalletException {
    public NullIdException(Class<?> clazz) {
        super("Id of class: " + clazz.getName() + " can not be null.");
    }
}
