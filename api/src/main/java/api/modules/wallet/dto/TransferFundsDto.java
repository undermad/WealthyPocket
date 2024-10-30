package api.modules.wallet.dto;

import wallet.commands.TransferFunds;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferFundsDto(String sendingWalletId,
                               String receivingWallet,
                               Double amount,
                               String currencyCode,
                               String references)
{
    
    public static TransferFunds mapToCommand(UUID userID, TransferFundsDto transferFundsDto)
    {
        return TransferFunds.builder()
                .sendingUserId(userID)
                .sendingWalletId(UUID.fromString(transferFundsDto.sendingWalletId()))
                .receivingWalletId(UUID.fromString(transferFundsDto.receivingWallet()))
                .amount(BigDecimal.valueOf(transferFundsDto.amount()))
                .currencyCode(transferFundsDto.currencyCode())
                .references(transferFundsDto.references())
                .build();
    }
}
