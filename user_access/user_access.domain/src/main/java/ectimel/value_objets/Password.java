package ectimel.value_objets;

import ectimel.exceptions.ValueNotValid;
import ectimel.validators.PasswordValidator;

public record Password(String password) {
    
    public static final PasswordValidator PASSWORD_VALIDATOR = new PasswordValidator();
    
    public Password {
        if(!PASSWORD_VALIDATOR.isValid(password)) {
            throw new ValueNotValid("Password is not valid. Make sure is properly encrypted.");
        }
    }
}
