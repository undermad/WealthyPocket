package ectimel.value_objets;

import ectimel.exceptions.ValueNotValid;
import ectimel.validators.PasswordValidator;

public record Password(String value) {
    
    public static final PasswordValidator PASSWORD_VALIDATOR = new PasswordValidator();
    
    public Password {
        if(!PASSWORD_VALIDATOR.isValid(value)) {
            throw new ValueNotValid("Password is not valid. Make sure is properly encrypted.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        return ((Password) obj).value().equals(this.value);
    }
}
