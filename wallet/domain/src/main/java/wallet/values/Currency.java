package wallet.values;

import wallet.exceptions.InvalidCurrency;
import wallet.validators.CurrencyValidator;

public record Currency(String currencyCode) {

    public static final CurrencyValidator CURRENCY_VALIDATOR = new CurrencyValidator();

    public Currency {
        if (!CURRENCY_VALIDATOR.isValid(currencyCode))
            throw new InvalidCurrency("Currency code " + currencyCode + " is not valid");
    }


}
