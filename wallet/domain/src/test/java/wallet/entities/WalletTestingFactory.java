package wallet.entities;

import wallet.values.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class WalletTestingFactory
{
    
    public static Owner createOwner()
    {
        return Owner.builder()
                .userId(new UserId(UUID.randomUUID()))
                .wallets(new ArrayList<>())
                .build();
    }
    
}
