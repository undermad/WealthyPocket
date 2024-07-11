package ectimel.value_objects;

import ectimel.exceptions.NullException;

public record RoleName(String value) {
    
    public RoleName {
        if(value == null) throw new NullException("Value RoleName can not be null");
    }
    
    public static RoleName createRegularUserName() {
        return new RoleName("User");
    }
    
    public static RoleName createAdminName() {
        return new RoleName("Admin");
    }
    
}
