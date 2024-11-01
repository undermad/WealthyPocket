package wallet.values;

import wallet.exceptions.NullException;

import java.math.BigDecimal;

public record MoneyAmount(BigDecimal value)
{
    public MoneyAmount
    {
        if (value == null) throw new NullException("Value money can not be null.");
    }
}
