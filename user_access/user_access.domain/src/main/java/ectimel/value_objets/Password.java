package ectimel.value_objets;

import ectimel.aggregates.ValueObject;
import ectimel.exceptions.ValueNotValid;
import ectimel.validators.PasswordValidator;
import lombok.Getter;

@Getter
public class Password extends ValueObject {
    
    private String value;
    
    public static final PasswordValidator PASSWORD_VALIDATOR = new PasswordValidator();
    
    public Password(String value) {
        if(!PASSWORD_VALIDATOR.isValid(value)) {
            throw new ValueNotValid("Password is not valid. Make sure is properly encrypted.");
        }
        this.value = value;
    }

}
