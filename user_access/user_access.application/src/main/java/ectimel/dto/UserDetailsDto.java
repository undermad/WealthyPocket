package ectimel.dto;


import java.util.Set;

public record UserDetailsDto(String email, String password, Set<String> roles) {
}
