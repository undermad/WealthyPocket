package ectimel.exceptions;

import ectimel.entities.Role;

public class RoleAlreadyGrantedException extends EWalletException {
    public RoleAlreadyGrantedException(Role role) {
        super("Role: " + role.getRoleName() + " already granted.");
    }
}
