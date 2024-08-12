package wallet.entities;

import ectimel.aggregates.EntityObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import wallet.values.MoneyAmount;
import wallet.values.MoneyCurrency;
import wallet.values.MoneyId;


@AllArgsConstructor
@Builder
@Entity
@Table(name = "money")
public class Money extends EntityObject<MoneyId> {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;
    
    @Embedded
    private MoneyAmount amount;
    
    @Embedded
    private MoneyCurrency currencyCode;

    protected Money() {
        // only for hibernate
    }
    
    
    
}
