package wallet.entities;


import ectimel.aggregates.AggregateRoot;
import jakarta.persistence.*;
import wallet.values.WalletId;

import java.util.List;

@Entity
@Table(name = "wallet")
public class Wallet extends AggregateRoot<WalletId> {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
    
    @OneToMany(mappedBy = "wallet", fetch = FetchType.EAGER)
    private List<Money> money;
    
}
