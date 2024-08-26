package wallet.entities;

import ectimel.aggregates.AggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import wallet.values.OwnerId;
import wallet.values.UserId;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "owner")
public class Owner extends AggregateRoot<OwnerId> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false, unique = true))
    private UserId userId;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Wallet> wallets;

    protected Owner() {
        // only for hibernate
    }

    @Override
    public void validate() {
        
    }


}
