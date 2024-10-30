package wallet.entities;

import wallet.aggregates.AggregateRoot;
import jakarta.persistence.*;
import lombok.*;
import wallet.exceptions.WalletNotFoundException;
import wallet.factories.WalletFactory;
import wallet.values.Currency;
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

    public void addNewWallet(@NonNull Currency currency)
    {
        wallets.add(WalletFactory.createEmptyWallet(this, currency));
    }
    
    public Optional<Wallet> findWallet(@NonNull WalletId walletId)
    {
        return wallets.stream().filter(wallet -> wallet.getId().equals(walletId)).findAny();
    }

    public void transfer(@NonNull Wallet sendingWallet, @NonNull Wallet recievingWallet, @NonNull Money amount)
    {
        sendingWallet.deductFunds(amount);
        recievingWallet.addFunds(amount);
    }

    public void deposit(@NonNull WalletId receiverWalletId, @NonNull Money moneyToAdd)
    {
        var targetWallet = findWallet(receiverWalletId);

        if (targetWallet.isEmpty()) throw new WalletNotFoundException(receiverWalletId);

        targetWallet.get().addFunds(moneyToAdd);
    }

    public void withdraw(@NonNull WalletId receiverWalletId, @NonNull Money moneyToDeduct)
    {
        var targetWallet = findWallet(receiverWalletId);

        if(targetWallet.isEmpty()) throw new WalletNotFoundException(receiverWalletId);

        targetWallet.get().deductFunds(moneyToDeduct);
    }

}
