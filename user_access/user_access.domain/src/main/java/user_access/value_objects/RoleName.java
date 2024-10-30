package user_access.value_objects;

import wallet.exceptions.NullException;

public record RoleName(String value) {
    
    public RoleName {
        if(value == null) throw new NullException("Value RoleName can not be null");
    }
    
    
    public static RoleName createAdminName() {
        return new RoleName("ROLE_ADMIN");
    }
    
    public static RoleName createActiveRoleName() {
        return new RoleName("ROLE_ACTIVE_USER");
    }
    
    public static RoleName createInactiveRoleName() {
        return new RoleName("ROLE_INACTIVE_USER");
    }
}
