package user_access.policies;

import user_access.value_objects.Email;
import user_access.value_objects.Password;

import java.time.LocalDate;

public record UserRegistrationData(
        Email email,
        Password password,
        LocalDate bornDate
) {
}
