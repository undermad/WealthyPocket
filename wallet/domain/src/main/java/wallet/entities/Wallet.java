package wallet.entities;


import ectimel.aggregates.AggregateRoot;
import jakarta.persistence.*;
import wallet.values.UserId;
import wallet.values.WalletId;

import java.util.List;

@Entity
@Table(name = "wallet")
public class Wallet extends AggregateRoot<WalletId> {
    
    @OneToMany(mappedBy = "wallet", fetch = FetchType.EAGER)
    private List<Money> money;
    
}
