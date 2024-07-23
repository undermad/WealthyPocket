package user_access.exceptions;

import ectimel.exceptions.EWalletException;
import user_access.entities.Role;

public class RoleAlreadyGrantedException extends EWalletException {
    public RoleAlreadyGrantedException(Role role) {
        super("Role: " + role.getRoleName() + " already granted.");
    }
}
