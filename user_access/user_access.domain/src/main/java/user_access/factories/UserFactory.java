package user_access.factories;

import user_access.entities.Role;
import user_access.entities.User;
import user_access.policies.UserPolicy;
import user_access.policies.UserRegistrationData;
import user_access.value_objects.UserId;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserFactory implements AccountFactory {

    private List<UserPolicy> policies;

    public UserFactory(List<UserPolicy> policies) {
        this.policies = policies;
    }


    @Override
    public User createUser(UserRegistrationData registrationData) {

        var applicablePolicies = policies.stream()
                .filter(policy -> policy.isApplicable(registrationData))
                .collect(Collectors.toSet());
        
        var role = Role.createUserRole();
        var userId = new UserId(UUID.randomUUID());
        
        return new User(userId, registrationData.email(), registrationData.password(), Set.of(role));
    }

}
