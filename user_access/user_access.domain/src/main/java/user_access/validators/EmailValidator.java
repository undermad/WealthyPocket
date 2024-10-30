package user_access.validators;


import wallet.validators.Validator;

public class EmailValidator implements Validator<String> {
    @Override
    public boolean isValid(String value) {
        return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(value);
    }
}
