package wallet.values;

import ectimel.exceptions.NullException;
import wallet.exceptions.NegativeValueException;

import java.math.BigDecimal;
import java.math.BigInteger;

public record MoneyAmount(BigDecimal value)
{
    public MoneyAmount
    {
        if (value == null) throw new NullException("Value money can not be null.");
    }
}
