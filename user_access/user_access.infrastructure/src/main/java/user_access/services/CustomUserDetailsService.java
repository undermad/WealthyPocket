package user_access.services;

import user_access.value_objects.Email;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @PersistenceContext(unitName = "puReadUserAccess")
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var typedQuery = entityManager.createQuery(
                """
                        SELECT u FROM User u WHERE u.email = :email
                        """,
                user_access.entities.User.class
        );
        typedQuery.setParameter("email", new Email(email));

        var user = typedQuery.getSingleResult();

        var authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().value()))
                .collect(Collectors.toSet());

        return new User(user.getEmail().value(), user.getPassword().value(), authorities);
    }
}
