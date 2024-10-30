package wallet;

import lombok.Builder;
import wallet.message_broker.Event;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record FundsTransferred(
        UUID id,
        UUID sendingWallet,
        UUID receivingWalletId,
        BigDecimal amount,
        String references
) implements Event
{
    @Override
    public UUID getId()
    {
        return id;
    }
}
