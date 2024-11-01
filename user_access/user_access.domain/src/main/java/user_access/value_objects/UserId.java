package user_access.value_objects;

import wallet.exceptions.NullIdException;

import java.io.Serializable;
import java.util.UUID;

public record UserId(UUID id) implements Serializable {
    public UserId {
        if(id == null) throw new NullIdException(this.getClass());
    }
}
