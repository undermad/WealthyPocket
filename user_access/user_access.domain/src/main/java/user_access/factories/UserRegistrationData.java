package user_access.factories;

import lombok.Builder;
import user_access.value_objects.BornDate;
import user_access.value_objects.Country;
import user_access.value_objects.Email;
import user_access.value_objects.Password;


@Builder
public record UserRegistrationData(
        Email email,
        Password password,
        BornDate bornDate,
        Country country
) {
}
