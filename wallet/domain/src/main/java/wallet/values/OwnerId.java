package wallet.values;

import wallet.exceptions.NullIdException;

import java.io.Serializable;
import java.util.UUID;

public record OwnerId(UUID id) implements Serializable {
    
    public OwnerId {
        if(id == null) throw new NullIdException(OwnerId.class);
    }
}
