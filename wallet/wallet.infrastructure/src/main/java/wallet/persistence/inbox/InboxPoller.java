package wallet.persistence.inbox;

import ectimel.inbox.InboxRepository;
import ectimel.outbox.Poller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("walletInboxPoller")
public class InboxPoller implements Poller {
    
    
    private final InboxRepository<WalletInboxMessage> inboxRepository;

    public InboxPoller(@Qualifier("walletInboxRepository") InboxRepository<WalletInboxMessage> inboxRepository) {
        this.inboxRepository = inboxRepository;
    }
    
    @Scheduled(fixedRate = 5000L)
    public void handleInboxMessagesTask() {
        poll();
    }
    
    @Override
    public void poll() {
        var messages = inboxRepository.getAllMessages();
        messages.forEach(message ->{
            System.out.println(message.getPayload());
            System.out.println(message.getId());
        });
    }
}
