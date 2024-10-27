package wallet.entities;

import ectimel.aggregates.EntityObject;
import ectimel.exceptions.NullException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import wallet.factories.StandardMoneyFactory;
import wallet.values.MoneyAmount;
import wallet.values.WalletId;

import java.util.List;


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

    public void addFounds(Money moneyToAdd)
    {
        if(moneyToAdd == null) throw new NullException("Money can not be null");
        
        var targetMoney = money.stream().filter(m -> m.getCurrencyCode().equals(moneyToAdd.getCurrencyCode()))
                .findFirst();

        if (targetMoney.isPresent()) targetMoney.get().addFounds(moneyToAdd);
        else {
            moneyToAdd.setWallet(this);
            money.add(moneyToAdd);
        }
    }

}
