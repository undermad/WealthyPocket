package user_access.value_objects;

import ectimel.exceptions.NullIdException;

import java.io.Serializable;

public record RoleId(Integer id) implements Serializable {

    public RoleId {
        if(id == null) throw new NullIdException(this.getClass());
    }
    
    
    public static RoleId createAdminId() {
        return new RoleId(1);
    }
    
    public static RoleId createActiveRoleId() {
        return new RoleId(2);
    }
    
    public static RoleId createInactiveRoleId() { return new RoleId(3); }
    
}
