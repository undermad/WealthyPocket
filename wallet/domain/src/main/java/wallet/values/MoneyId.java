package wallet.values;

import wallet.exceptions.NullIdException;

import java.io.Serializable;
import java.util.UUID;

public record MoneyId(UUID id) implements Serializable {

    public MoneyId {
        if(id == null) throw new NullIdException(MoneyId.class);
    }
    
}
