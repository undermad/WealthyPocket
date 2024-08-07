package wallet.entities;

import ectimel.aggregates.EntityObject;
import jakarta.persistence.*;
import wallet.values.MoneyAmount;
import wallet.values.MoneyId;

import java.util.Currency;

@Entity
@Table(name = "money")
public class Money extends EntityObject<MoneyId> {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;
    
    @Embedded
    private MoneyAmount amount;
    
    @Embedded
    @AttributeOverride(name = "currencyCode", column = @Column(name = "currency", nullable = false))
    private Currency currency;
}
