package wallet.values;

import wallet.exceptions.NullIdException;

import java.util.UUID;

public record UserId(UUID id) {
    public UserId {
        if(id == null) throw new NullIdException(this.getClass());
    }
}