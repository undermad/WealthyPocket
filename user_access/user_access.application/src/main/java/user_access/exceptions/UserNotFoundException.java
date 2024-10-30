package user_access.exceptions;

import wallet.exceptions.EWalletException;

public class UserNotFoundException extends EWalletException {
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
