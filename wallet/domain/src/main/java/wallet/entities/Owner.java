package wallet.entities;

import ectimel.aggregates.AggregateRoot;
import ectimel.exceptions.NullException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import wallet.exceptions.ReceiverWalletNotFoundException;
import wallet.values.MoneyAmount;
import wallet.values.OwnerId;
import wallet.values.UserId;
import wallet.values.WalletId;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "owner")
public class Owner extends AggregateRoot<OwnerId>
{

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false, unique = true))
    private UserId userId;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Wallet> wallets;

    protected Owner()
    {
        // only for hibernate
    }

    @Override
    public void validate()
    {

    }

    public void addFounds(WalletId receiverWalletId, Money moneyToAdd)
    {
        if(receiverWalletId == null || moneyToAdd == null) throw new NullException("Receiver or moneyToAdd can not be null.");
        
        var targetWallet = wallets.stream().filter(wallet -> wallet.getId().equals(receiverWalletId))
                .findFirst();
        
        if(targetWallet.isEmpty()) throw new ReceiverWalletNotFoundException(receiverWalletId);
        
        targetWallet.get().addFounds(moneyToAdd);
    }


}
