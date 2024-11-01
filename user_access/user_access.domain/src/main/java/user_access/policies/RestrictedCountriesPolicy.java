package user_access.policies;

import wallet.policies.Policy;
import user_access.exceptions.CountryNotAllowed;

import java.util.Arrays;

@Policy
public class RestrictedCountriesPolicy implements UserPolicy {
    @Override
    public Boolean isApplicable(UserRegistrationData applicableData) {

        if (Arrays.stream(RestrictedCountries.values())
                .anyMatch(restrictedCountries -> restrictedCountries.isoCode
                        .equalsIgnoreCase(applicableData.country().value()))) {
            throw new CountryNotAllowed("Country: " + applicableData.country().value() + " is not supported."); 
        }
        
        return true;
    }

    public enum RestrictedCountries {
        URUGUAY("UY");

        private final String isoCode;


        RestrictedCountries(String isoCode) {
            this.isoCode = isoCode;
        }

    }
}
