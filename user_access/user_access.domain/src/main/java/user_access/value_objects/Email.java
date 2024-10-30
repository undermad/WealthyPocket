package user_access.value_objects;

import wallet.exceptions.ValueNotValid;
import user_access.validators.EmailValidator;

public record Email(String value) {
    
    public static final EmailValidator VALIDATOR = new EmailValidator();
    
    public Email {
        if(!VALIDATOR.isValid(value)) {
            throw new ValueNotValid("Email: " + value + " is not valid.");
        }
    }
    
}
