package wallet.values;

import wallet.exceptions.NullIdException;

import java.io.Serializable;
import java.util.UUID;

public record WalletId(UUID id) implements Serializable {
    public WalletId {
        if(id == null) throw new NullIdException(this.getClass());
    }
}
