package user_access.exceptions;

import ectimel.exceptions.EWalletException;

public class UserNotFoundException extends EWalletException {
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
