package ectimel.value_objects;

import ectimel.exceptions.ValueNotValid;
import ectimel.validators.PasswordValidator;

public record Password(String value) {
    
    public static final PasswordValidator PASSWORD_VALIDATOR = new PasswordValidator();
    
    public Password {
        if(!PASSWORD_VALIDATOR.isValid(value)) {
            throw new ValueNotValid("Password is not valid. Make sure is properly encrypted.");
        }
    }

}
