package ectimel.queries;

import ectimel.cqrs.queries.Query;
import ectimel.dto.UserDetailsDto;
import org.springframework.security.core.userdetails.UserDetails;

public record LoadUserByUsername(String email) implements Query<UserDetails> {
}
