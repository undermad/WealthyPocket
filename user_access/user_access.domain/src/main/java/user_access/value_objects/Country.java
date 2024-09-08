package user_access.value_objects;

import user_access.exceptions.InvalidCountryName;

import java.util.Arrays;
import java.util.Locale;

public record Country(String value) {

    public static Country fromName(String value) {
        return new Country(Arrays.stream(Locale.getAvailableLocales())
                .filter(l -> l.getDisplayCountry().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new InvalidCountryName("Country name: " + value + " is not supported."))
                .getCountry());
    }

}
