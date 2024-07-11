package ectimel.exceptions;

import ectimel.value_objects.Email;

public class UserAlreadyExistException extends EWalletException {
    public UserAlreadyExistException(Email email) {
        super("User with email " + email.value() + " already exist.");
    }
}
