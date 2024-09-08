package wallet.policies;

import wallet.values.UserId;


public record WalletPolicyData(
        UserId userId,
        String country
) {
    
}
