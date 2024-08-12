package wallet.entities;

import ectimel.aggregates.AggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import wallet.values.OwnerId;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "owner")
public class Owner extends AggregateRoot<OwnerId> {

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Wallet> wallets;

    protected Owner() {
        // only for hibernate
    }
    
    

}
