package user_access.exceptions;

import wallet.exceptions.EWalletException;

public class AdultsOnlyException extends EWalletException {
    public AdultsOnlyException() {
        super("Only at least 18 years old users are allowed.");
    }
}
