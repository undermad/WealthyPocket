package user_access.policies;

import lombok.Builder;
import user_access.value_objects.BornDate;
import user_access.value_objects.Country;
import user_access.value_objects.Email;
import user_access.value_objects.Password;

import java.time.LocalDate;

@Builder
public record UserRegistrationData(
        Email email,
        Password password,
        BornDate bornDate,
        Country country
) {
}
