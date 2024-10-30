package wallet.exceptions;

import java.util.UUID;

public class OwnerAlreadyExist extends EWalletException {
    public OwnerAlreadyExist(String message) {
        super(message);
    }
}
