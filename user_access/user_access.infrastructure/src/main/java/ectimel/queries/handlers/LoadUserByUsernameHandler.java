package ectimel.queries.handlers;

import ectimel.cqrs.queries.AsyncHandler;
import ectimel.cqrs.queries.QueryHandler;
import ectimel.dto.UserDetailsDto;
import ectimel.entities.User;
import ectimel.queries.LoadUserByUsername;
import ectimel.value_objects.Email;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@AsyncHandler
public class LoadUserByUsernameHandler implements QueryHandler<LoadUserByUsername, UserDetailsDto> {

    @PersistenceContext(unitName = "puReadUserAccess")
    private EntityManager entityManager;

    @Override
    public CompletableFuture<UserDetailsDto> handle(LoadUserByUsername query) {

        var typedQuery = entityManager.createQuery(
                """
                        SELECT u FROM User u WHERE u.email = :email
                        """,
                User.class
        );
        typedQuery.setParameter("email", new Email(query.email()));

        var user = typedQuery.getSingleResult();

        return CompletableFuture.completedFuture(new UserDetailsDto(
                user.getEmail().value(),
                user.getPassword().value(),
                user.getRoles().stream()
                        .map(role -> role.getRoleName().value())
                        .collect(Collectors.toSet())));
    }
}
