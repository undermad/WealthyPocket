package ectimel.services;

public interface JwtProvider {
    String generateToken(String email);
    String getSubject(String jwt);
    Boolean validateToken(String token);
}
