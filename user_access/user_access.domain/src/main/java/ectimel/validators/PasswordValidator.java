package ectimel.validators;

public class PasswordValidator implements Validator<String> {
    
    // Check if password is BCrypt encoded
    
    @Override
    public boolean isValid(String value) {
        return isBCryptEncoded(value);
    }

    public static boolean isBCryptEncoded(String password) {
        if (password == null || password.length() != 60) {
            return false;
        }

        // Regular expression to validate the overall format
        String bcryptPattern = "^\\$2[ayb]\\$\\d{2}\\$.{53}$";
        if (!password.matches(bcryptPattern)) {
            return false;
        }

        // Extract the cost factor and ensure it is within the valid range
        String[] parts = password.split("\\$");
        int cost;
        try {
            cost = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        if (cost < 4 || cost > 31) {
            return false;
        }

        // Extract and validate the characters in salt and hash (Base64 without +, /, and =)
        String saltAndHash = parts[3];
        String salt = saltAndHash.substring(0, 22);
        String hash = saltAndHash.substring(22);

        String base64Pattern = "^[A-Za-z0-9./]+$";
        return salt.matches(base64Pattern) && hash.matches(base64Pattern);
    }
}
