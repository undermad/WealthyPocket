package user_access.exceptions;

import ectimel.exceptions.EWalletException;
import user_access.value_objects.Email;

public class UserAlreadyExistException extends EWalletException {
    public UserAlreadyExistException(Email email) {
        super("User with email " + email.value() + " already exist.");
    }
}
