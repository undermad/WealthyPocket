package user_access.policies;

import ectimel.policies.Policy;
import user_access.exceptions.CountryNotAllowed;
import user_access.factories.UserRegistrationData;

import java.util.Arrays;

@Policy
public class RestrictedCountriesPolicy implements UserPolicy {
    @Override
    public void isApplicable(UserRegistrationData applicableData) {

        if (Arrays.stream(RestrictedCountries.values())
                .anyMatch(restrictedCountries -> restrictedCountries.isoCode
                        .equalsIgnoreCase(applicableData.country().value()))) {
            throw new CountryNotAllowed("Country: " + applicableData.country().value() + " is not supported."); 
        }

    }

    public enum RestrictedCountries {
        URUGUAY("UY");

        private final String isoCode;


        RestrictedCountries(String isoCode) {
            this.isoCode = isoCode;
        }

    }
}
