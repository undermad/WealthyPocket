package wallet.entities;

import ectimel.aggregates.EntityObject;
import jakarta.persistence.*;
import lombok.*;
import wallet.exceptions.CurrencyDoesntMatchException;
import wallet.exceptions.NegativeValueException;
import wallet.exceptions.NotSufficientBalance;
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
    @AttributeOverride(name = "value", column = @Column(name = "currency_code", nullable = false))
    private Currency currencyCode;

    protected Money()
    {
        // only for hibernate
    }

    public void addFunds(@NonNull Money moneyToAdd)
    {
        validateCurrency(moneyToAdd);
        validateNonNegativeAmount(moneyToAdd);
        
        var newAmount = this.amount.value().add(moneyToAdd.amount.value());
        setAmount(new MoneyAmount(newAmount));
    }

    public void deductFunds(@NonNull Money moneyToDeduct)
    {
        validateCurrency(moneyToDeduct);
        validateNonNegativeAmount(moneyToDeduct);
        validateSufficientFunds(moneyToDeduct);
        
        var newAmount = this.amount.value().subtract(moneyToDeduct.amount.value());
        setAmount(new MoneyAmount(newAmount));
    }
    
    private void validateCurrency(Money moneyToValidate)
    {
        if(!moneyToValidate.getCurrencyCode().equals(currencyCode)) throw new CurrencyDoesntMatchException("Currency must be the same");
    }
    
    private void validateNonNegativeAmount(Money moneyToValidate)
    {
        if(moneyToValidate.amount.value().doubleValue() < 0) throw new NegativeValueException("Can not deduct negative value.");
    }

    private void validateSufficientFunds(Money moneyToDeduct)
    {
        if(this.amount.value().subtract(moneyToDeduct.amount.value()).doubleValue() < 0)
            throw new NotSufficientBalance("Wallet with id: " + wallet.getId() + " doesn't have enough money");
    }

}
