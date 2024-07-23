package user_access.services;

public interface JwtProvider {
    String generateToken(String email);
    String getSubject(String jwt);
    Boolean validateToken(String token);
}
