package wallet.validators;

import java.util.Currency;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyValidator implements Validator<String> {
    
    private final Set<String> currenciesCodes = Currency.getAvailableCurrencies()
            .stream()
            .map(Currency::getCurrencyCode)
            .collect(Collectors.toSet());
    
    @Override
    public boolean isValid(String value) {
        return currenciesCodes.contains(value);    
    }
    
}
