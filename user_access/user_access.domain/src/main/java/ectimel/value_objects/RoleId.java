package ectimel.value_objects;

import ectimel.aggregates.ValueObject;
import ectimel.exceptions.NullIdException;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
public class RoleId extends ValueObject implements Serializable {
    
    private Integer id;

    protected RoleId () {
        // hibernate will override id with actual id from database.
    }

    private RoleId(Integer id) {
        if(id == null) throw new NullIdException(this.getClass());
        this.id = id;
    }
    
    
    public static RoleId createAdminId() {
        return new RoleId(1);
    }
    
    public static RoleId createUserId() {
        return new RoleId(2);
    }
    
}
