package user_access.queries;

import ectimel.cqrs.queries.Query;
import org.springframework.security.core.userdetails.UserDetails;

public record LoadUserByUsername(String email) implements Query<UserDetails> {
}
