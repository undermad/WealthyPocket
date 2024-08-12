package wallet.entities;

import ectimel.aggregates.EntityObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import wallet.values.WalletId;

import java.util.List;


@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "wallet")
public class Wallet extends EntityObject<WalletId> {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
    
    @OneToMany(mappedBy = "wallet", fetch = FetchType.EAGER)
    private List<Money> money;

    protected Wallet() {
        // only for hibernate
    }
    
}
