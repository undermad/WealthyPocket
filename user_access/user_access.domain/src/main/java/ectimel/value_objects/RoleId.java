package ectimel.value_objects;

import ectimel.exceptions.NullIdException;

import java.io.Serializable;

public record RoleId(Integer id) implements Serializable {

    public RoleId {
        if(id == null) throw new NullIdException(this.getClass());
    }
    
    
    public static RoleId createAdminId() {
        return new RoleId(1);
    }
    
    public static RoleId createUserId() {
        return new RoleId(2);
    }
    
}
