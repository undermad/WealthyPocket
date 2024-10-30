package wallet.entities;

import wallet.aggregates.EntityObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import wallet.exceptions.CurrencyDoesntMatchException;
import wallet.values.Currency;
import wallet.values.WalletId;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity
@Table(name = "wallet")
public class Wallet extends EntityObject<WalletId>
{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Money> money;
    
    protected Wallet()
    {
        // only for hibernate
    }
    

    
    protected void addFunds(@NonNull Money moneyToAdd)
    {
        var targetMoney = findMoneyByCurrency(moneyToAdd.getCurrencyCode());

        if (targetMoney.isPresent()) {
            targetMoney.get().addFunds(moneyToAdd);
            return;
        }

        moneyToAdd.setWallet(this);
        money.add(moneyToAdd);
    }

    protected void deductFunds(@NonNull Money moneyToDeduct)
    {
        var targetMoney = findMoneyByCurrency(moneyToDeduct.getCurrencyCode());

        if (targetMoney.isEmpty())
            throw new CurrencyDoesntMatchException("You don't have any: " + moneyToDeduct.getCurrencyCode().value());
        
        targetMoney.get().deductFunds(moneyToDeduct);
    }

    private Optional<Money> findMoneyByCurrency(Currency currency)
    {
        return money.stream().filter(m -> m.getCurrencyCode().equals(currency)).findFirst();
    }

}
