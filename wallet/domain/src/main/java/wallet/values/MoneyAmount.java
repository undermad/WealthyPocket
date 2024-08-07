package wallet.values;

import ectimel.exceptions.NullException;

import java.math.BigInteger;

public record MoneyAmount(BigInteger amount) {
    public MoneyAmount {
        if (amount == null) throw new NullException("Value money can not be null.");
    }
}
