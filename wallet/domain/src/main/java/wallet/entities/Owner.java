package wallet.entities;

import ectimel.aggregates.AggregateRoot;
import jakarta.persistence.*;
import lombok.*;
import wallet.exceptions.WalletNotFoundException;
import wallet.values.OwnerId;
import wallet.values.UserId;
import wallet.values.WalletId;

import java.util.List;
import java.util.Optional;

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

    public void deposit(@NonNull WalletId receiverWalletId, @NonNull Money moneyToAdd)
    {
        var targetWallet = findOwnerWallet(receiverWalletId);

        if (targetWallet.isEmpty()) throw new WalletNotFoundException(receiverWalletId);

        targetWallet.get().addFunds(moneyToAdd);
    }

    public void withdraw(@NonNull WalletId receiverWalletId, @NonNull Money moneyToDeduct)
    {
        var targetWallet = findOwnerWallet(receiverWalletId);
        
        if(targetWallet.isEmpty()) throw new WalletNotFoundException(receiverWalletId);

        targetWallet.get().deductFunds(moneyToDeduct);

    }

    private Optional<Wallet> findOwnerWallet(WalletId walletId)
    {
        return wallets.stream().filter(wallet -> wallet.getId().equals(walletId)).findFirst();
    }

}
