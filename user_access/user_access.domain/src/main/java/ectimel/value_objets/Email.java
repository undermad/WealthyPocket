package ectimel.value_objets;

import ectimel.exceptions.ValueNotValid;

public record Email(String email) {
    public static final EmailValidator VALIDATOR = new EmailValidator();
    
    public Email {
        if(!VALIDATOR.isValid(email)) {
            throw new ValueNotValid("Email: " + email + " is not valid.");
        }
    }
    
}
