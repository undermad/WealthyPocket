package wallet.commands;

import lombok.Builder;
import wallet.cqrs.commands.Command;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record TransferFunds(
        UUID sendingUserId,
        UUID sendingWalletId,
        UUID receivingWalletId,
        BigDecimal amount,
        String currencyCode,
        String references
) implements Command
{
}
