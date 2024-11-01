package wallet.commands.handlers;

import org.springframework.beans.factory.annotation.Qualifier;
import wallet.FundsTransferredEvent;
import wallet.cqrs.commands.CommandHandler;
import wallet.cqrs.commands.Handler;
import org.springframework.transaction.annotation.Transactional;
import wallet.commands.TransferFunds;
import wallet.exceptions.IncorrectDetailsException;
import wallet.exceptions.WalletNotFoundException;
import wallet.factories.MoneyFactory;
import wallet.outbox.OutboxRepository;
import wallet.repositories.OwnerRepository;
import wallet.values.Currency;
import wallet.values.UserId;
import wallet.values.WalletId;

import java.util.UUID;

@Handler
public class TransferFundsHandler implements CommandHandler<TransferFunds>
{

    private final OwnerRepository ownerRepository;
    private final OutboxRepository outboxRepository;

    public TransferFundsHandler(OwnerRepository ownerRepository,
                                @Qualifier("walletOutbox") OutboxRepository outboxRepository)
    {
        this.ownerRepository = ownerRepository;
        this.outboxRepository = outboxRepository;
    }

    @Transactional("writeTransactionManagerWallet")
    @Override
    public void handle(TransferFunds command)
    {
        var sendingOwner = ownerRepository.get(new UserId(command.sendingUserId()))
                .orElseThrow(() -> new IncorrectDetailsException("Can not find sending wallet."));
        var receivingOwner = ownerRepository.getByWalletId(new WalletId(command.receivingWalletId()))
                .orElseThrow(() -> new IncorrectDetailsException("Can not find receiving wallet."));
        var sendingWallet = sendingOwner.findWallet(new WalletId(command.sendingWalletId()))
                .orElseThrow(() -> new WalletNotFoundException(new WalletId(command.sendingWalletId())));
        var receivingWallet = receivingOwner.findWallet(new WalletId(command.receivingWalletId()))
                .orElseThrow(() -> new WalletNotFoundException(new WalletId(command.receivingWalletId())));
        
        var amount = MoneyFactory.createTransferableMoney(command.amount().doubleValue(), new Currency(command.currencyCode()));

        var references = command.references();
        
        sendingOwner.transfer(sendingWallet, receivingWallet, amount);

        var fundsTransferredEvent = FundsTransferredEvent.builder()
                .id(UUID.randomUUID())
                .sendingWallet(sendingWallet.getId().id())
                .receivingWalletId(receivingWallet.getId().id())
                .amount(amount.getAmount().value())
                .references(references)
                .build();
        
        ownerRepository.updateAsync(sendingOwner);
        ownerRepository.updateAsync(receivingOwner);
        outboxRepository.saveMessage(fundsTransferredEvent);
    }
    
}
