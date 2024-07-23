package user_access.validators;


import ectimel.validators.Validator;

public class EmailValidator implements Validator<String> {
    @Override
    public boolean isValid(String value) {
        return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(value);
    }
}
