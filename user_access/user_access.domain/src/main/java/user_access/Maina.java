package user_access;

import user_access.exceptions.InvalidCountryName;
import user_access.policies.RestrictedCountriesPolicy;

import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

public class Maina {

    public static void main(String[] args) {
        String[] countryCodes = Locale.getISOCountries();

        // Iterate through the country codes
        for (String countryCode : countryCodes) {
            // Create a Locale object using the country code
            Locale locale = new Locale("", countryCode);

            System.out.println(locale.getCountry());

            // Get the display name of the country
            String countryName = locale.getDisplayCountry();

            System.out.println(Currency.getInstance(locale));


            // Print the country code and name
            System.out.println(countryCode + " - " + countryName);


        }
        Arrays.stream(Locale.getAvailableLocales())
                .anyMatch(locale -> locale.getDisplayCountry().equalsIgnoreCase("France"));

        System.out.println(Arrays.stream(Locale.getAvailableLocales())
                .filter(l -> l.getDisplayCountry().equalsIgnoreCase("France"))
                .findFirst()
                .orElseThrow(() -> new InvalidCountryName("Country name: " + "France" + " is not supported."))
                .getCountry());

    }
}
