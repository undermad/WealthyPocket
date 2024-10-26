package api.modules.user_access.dto;

public record AuthenticateRequest(String email, String password)
{
}
