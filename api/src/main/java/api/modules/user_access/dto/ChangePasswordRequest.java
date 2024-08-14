package api.modules.user_access.dto;

public record ChangePasswordRequest(String oldPassword, String newPassword) {
}
