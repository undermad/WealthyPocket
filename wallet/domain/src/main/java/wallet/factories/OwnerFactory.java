package wallet.factories;

import org.apache.catalina.User;
import wallet.entities.Owner;
import wallet.policies.WalletPolicyData;
import wallet.values.Currency;
import wallet.values.OwnerId;
import wallet.values.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface OwnerFactory
{
    Owner createOwner(WalletPolicyData userId);

    static Owner createOwner(UserId userId, Currency defaultCurrency)
    {
        var owner = Owner.builder()
                .id(new OwnerId(UUID.randomUUID()))
                .userId(new UserId(UUID.randomUUID()))
                .build();

        var wallet = WalletFactory.createEmptyWallet(owner, defaultCurrency);
        
        owner.setWallets(new ArrayList<>(List.of(wallet)));
        
        return owner;
    }
}
