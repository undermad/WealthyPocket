package wallet.values;

import wallet.exceptions.InvalidCurrency;
import wallet.validators.CurrencyValidator;

public record Currency(String value) {

    public static final CurrencyValidator CURRENCY_VALIDATOR = new CurrencyValidator();

    public Currency {
        if (!CURRENCY_VALIDATOR.isValid(value))
            throw new InvalidCurrency("Currency code " + value + " is not valid");
    }


}
