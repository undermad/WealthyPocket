package ectimel.queries;

import ectimel.cqrs.queries.Query;
import ectimel.dto.UserDetailsDto;

public record LoadUserByUsername(String email) implements Query<UserDetailsDto> {
}
