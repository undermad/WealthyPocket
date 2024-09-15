package user_access.factories;

import user_access.entities.Role;
import user_access.entities.User;
import user_access.policies.UserPolicy;
import user_access.policies.UserRegistrationData;
import user_access.value_objects.UserId;

import java.util.List;
import java.util.Set;
import java.util.UUID;


public class UserFactory implements AccountFactory {

    private List<UserPolicy> policies;

    public UserFactory(List<UserPolicy> policies) {
        this.policies = policies;
    }


    @Override
    public User createUser(UserRegistrationData registrationData) {

        policies.forEach(policy -> policy.isApplicable(registrationData));

        return User.builder()
                .id(new UserId(UUID.randomUUID()))
                .email(registrationData.email())
                .password(registrationData.password())
                .roles(Set.of(Role.createInactiveUserRole()))
                .bornDate(registrationData.bornDate())
                .country(registrationData.country())
                .build();
    }

}
