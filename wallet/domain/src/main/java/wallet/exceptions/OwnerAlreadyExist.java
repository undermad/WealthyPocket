package wallet.exceptions;

import ectimel.exceptions.EWalletException;

import java.util.UUID;

public class OwnerAlreadyExist extends EWalletException {
    public OwnerAlreadyExist(UUID id) {
        super("Owner with id: " + id + " already exist.");
    }
}
