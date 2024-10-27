package wallet.entities;

import ectimel.aggregates.EntityObject;
import ectimel.exceptions.NullException;
import ectimel.exceptions.NullIdException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import wallet.exceptions.CurrencyDoesntMatchException;
import wallet.exceptions.NegativeValueException;
import wallet.values.MoneyAmount;
import wallet.values.Currency;
import wallet.values.MoneyId;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "money")
public class Money extends EntityObject<MoneyId>
{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "amount", nullable = false))
    private MoneyAmount amount;

    @Embedded
    @AttributeOverride(name = "currencyCode", column = @Column(name = "currency_code", nullable = false))
    private Currency currencyCode;

    protected Money()
    {
        // only for hibernate
    }

    public void addFounds(Money money)
    {
        if(money == null) throw new NullException("Money can not be null");
        if(!money.getCurrencyCode().equals(currencyCode)) throw new CurrencyDoesntMatchException("Currency must be the same.");
        if (money.amount.value().doubleValue() < 0) throw new NegativeValueException("Value can not be less than 0.");
        
        var newAmount = this.amount.value().add(money.amount.value());
        
        setAmount(new MoneyAmount(newAmount));
    }


}
