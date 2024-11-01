package user_access.value_objects;

import wallet.exceptions.ValueNotValid;
import user_access.validators.PasswordValidator;

public record Password(String value) {
    
    public static final PasswordValidator PASSWORD_VALIDATOR = new PasswordValidator();
    
    public Password {
        if(!PASSWORD_VALIDATOR.isValid(value)) {
            throw new ValueNotValid("Password is not valid. Make sure is properly encrypted.");
        }
    }

}
