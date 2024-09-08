package user_access.policies;

import ectimel.policies.Policy;
import user_access.exceptions.AdultsOnlyException;

import java.time.LocalDate;

@Policy
public class AdultOnlyPolicy implements UserPolicy {
    
    @Override
    public Boolean isApplicable(UserRegistrationData data) {
        
        var adultDate = data.bornDate().value().plusYears(18);
        
        var isAdult = adultDate.isBefore(LocalDate.now());
        
        if(!isAdult) {
            throw new AdultsOnlyException();
        }
        
        return true;
    }
}
