package wallet.entities;

import ectimel.aggregates.AggregateRoot;
import jakarta.persistence.*;
import wallet.values.OwnerId;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "owner")
public class Owner extends AggregateRoot<OwnerId> {

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;
    
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Wallet> wallets;
}
