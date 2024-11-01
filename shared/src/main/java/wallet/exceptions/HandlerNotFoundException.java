package wallet.exceptions;

public class HandlerNotFoundException extends EWalletException{
    public HandlerNotFoundException(String commandName) {
        super("Handler for command: " + commandName + " not found.");
    }
}
