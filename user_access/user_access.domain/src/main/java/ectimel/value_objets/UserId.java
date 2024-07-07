package ectimel.value_objets;

import ectimel.aggregates.ValueObject;
import ectimel.exceptions.NullIdException;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
public class UserId extends ValueObject
        implements Serializable {
    
    private UUID id;
    
    protected UserId () {
        this.id = UUID.randomUUID();
        // hibernate will override id with actual id from database.
    }
    
    public UserId(UUID id) {
        if(id == null) throw new NullIdException(this.getClass());
        this.id = id;
    }


}
