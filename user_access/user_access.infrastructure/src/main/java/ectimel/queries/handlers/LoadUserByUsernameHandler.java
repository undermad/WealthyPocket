package ectimel.queries.handlers;

import ectimel.cqrs.queries.AsyncHandler;
import ectimel.cqrs.queries.QueryHandler;
import ectimel.dto.UserDetailsDto;
import ectimel.entities.User;
import ectimel.queries.LoadUserByUsername;
import ectimel.value_objects.Email;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@AsyncHandler
public class LoadUserByUsernameHandler implements QueryHandler<LoadUserByUsername, UserDetails> {

    private final UserDetailsService userDetailsService;

    public LoadUserByUsernameHandler(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public CompletableFuture<UserDetails> handle(LoadUserByUsername query) {
        return CompletableFuture.completedFuture(userDetailsService.loadUserByUsername(query.email()));
    }
}
