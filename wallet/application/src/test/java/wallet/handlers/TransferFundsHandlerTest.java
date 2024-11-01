package wallet.handlers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import wallet.FundsTransferredEvent;
import wallet.commands.TransferFunds;
import wallet.commands.handlers.TransferFundsHandler;
import wallet.entities.Owner;
import wallet.exceptions.IncorrectDetailsException;
import wallet.exceptions.WalletNotFoundException;
import wallet.factories.MoneyFactory;
import wallet.factories.OwnerFactory;
import wallet.outbox.OutboxRepository;
import wallet.repositories.OwnerRepository;
import wallet.values.Currency;
import wallet.values.UserId;
import wallet.values.WalletId;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransferFundsHandlerTest
{

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    @Qualifier("walletOutbox")
    private OutboxRepository outboxRepository;

    @InjectMocks
    private TransferFundsHandler transferFundsHandler;

    private Owner sendingOwner;

    private Owner receivingOwner;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
        sendingOwner = OwnerFactory.createOwner(new UserId(UUID.randomUUID()), new Currency("GBP"));
        var sendingWalletId = sendingOwner.getWallets().getFirst().getId();
        sendingOwner.deposit(sendingWalletId, MoneyFactory.createTransferableMoney(100D, new Currency("GBP")));
        
        receivingOwner = OwnerFactory.createOwner(new UserId(UUID.randomUUID()), new Currency("GBP"));
        var receivingWalletId = receivingOwner.getWallets().getFirst().getId();
        receivingOwner.deposit(receivingWalletId, MoneyFactory.createTransferableMoney(100D, new Currency("GBP")));

        when(ownerRepository.get(any(UserId.class))).thenReturn(Optional.of(sendingOwner));
        when(ownerRepository.getByWalletId(any(WalletId.class))).thenReturn(Optional.of(receivingOwner));
        doNothing().when(ownerRepository).updateAsync(any());
        doNothing().when(outboxRepository).saveMessage(any());
    }

    @Test
    public void transferFunds_validTransfer()
    {
        var command = TransferFunds.builder()
                .sendingUserId(sendingOwner.getUserId().id())
                .sendingWalletId(sendingOwner.getWallets().getFirst().getId().id())
                .receivingWalletId(receivingOwner.getWallets().getFirst().getId().id())
                .amount(BigDecimal.valueOf(20))
                .currencyCode("GBP")
                .references("Testing references")
                .build();
        
        transferFundsHandler.handle(command);
        
        verify(ownerRepository, times(1)).get(any(UserId.class));
        verify(ownerRepository, times(1)).getByWalletId(any(WalletId.class));
        verify(ownerRepository, times(2)).updateAsync(any());
        verify(outboxRepository, times(1)).saveMessage(any(FundsTransferredEvent.class));
    }
    
    @Test
    public void handler_SendingOwnerNotFound()
    {
        var command = TransferFunds.builder()
                .sendingUserId(UUID.randomUUID())
                .build();
        
        when(ownerRepository.get(any(UserId.class))).thenReturn(Optional.empty());
        
        assertThrows(IncorrectDetailsException.class, () -> transferFundsHandler.handle(command));
    }
    
    @Test
    public void handler_ReceivingOwnerNotFound()
    {
        var command = TransferFunds.builder()
                .sendingUserId(UUID.randomUUID())
                .receivingWalletId(UUID.randomUUID())
                .build();
        
        when(ownerRepository.getByWalletId(any(WalletId.class))).thenReturn(Optional.empty());
        
        assertThrows(IncorrectDetailsException.class, () -> transferFundsHandler.handle(command));
    }
    
    @Test
    public void handler_sendingWalletNotFound()
    {
        var command = TransferFunds.builder()
                .sendingUserId(sendingOwner.getUserId().id())
                .sendingWalletId(UUID.randomUUID())
                .receivingWalletId(receivingOwner.getWallets().getFirst().getId().id())
                .amount(BigDecimal.valueOf(20))
                .currencyCode("GBP")
                .references("Testing references")
                .build();

        assertThrows(WalletNotFoundException.class, () -> transferFundsHandler.handle(command));
    }
    
    @Test
    public void handler_receivingWalletNotFound()
    {
        var command = TransferFunds.builder()
                .sendingUserId(sendingOwner.getUserId().id())
                .sendingWalletId(sendingOwner.getWallets().getFirst().getId().id())
                .receivingWalletId(UUID.randomUUID())
                .amount(BigDecimal.valueOf(20))
                .currencyCode("GBP")
                .references("Testing references")
                .build();
        
        assertThrows(WalletNotFoundException.class, () -> transferFundsHandler.handle(command));
    }


}
