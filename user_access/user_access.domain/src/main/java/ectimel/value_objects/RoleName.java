package ectimel.value_objects;

import ectimel.aggregates.ValueObject;
import ectimel.exceptions.NullException;
import lombok.Getter;

@Getter
public class RoleName extends ValueObject {
    
    private String value;

    private RoleName(String value) {
        if(value == null) throw new NullException("Value RoleName can not be null");
        this.value = value;
    }
    
    public static RoleName createRegularUserName() {
        return new RoleName("User");
    }
    
    public static RoleName createAdminName() {
        return new RoleName("Admin");
    }
    
}
