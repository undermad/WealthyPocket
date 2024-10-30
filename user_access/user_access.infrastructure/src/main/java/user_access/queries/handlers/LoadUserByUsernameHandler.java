package user_access.queries.handlers;

import wallet.cqrs.queries.AsyncHandler;
import wallet.cqrs.queries.QueryHandler;
import user_access.queries.LoadUserByUsername;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.concurrent.CompletableFuture;

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
