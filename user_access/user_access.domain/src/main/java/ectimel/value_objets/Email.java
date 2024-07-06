package ectimel.value_objets;

import ectimel.aggregates.ValueObject;
import ectimel.exceptions.ValueNotValid;
import ectimel.validators.EmailValidator;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Getter;

@Getter
public final class Email extends ValueObject {

    private String value;
    
    public static final EmailValidator VALIDATOR = new EmailValidator();
    
    public Email(String value) {
        if(!VALIDATOR.isValid(value)) {
            throw new ValueNotValid("Email: " + value + " is not valid.");
        }
        this.value = value;
    }

    public Email () {
        
    }
    
}
