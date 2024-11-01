package user_access.persistence.inbox;

import wallet.outbox.Poller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("userAccessInboxPoller")
public class InboxPoller implements Poller {
    
    
    
    
    @Scheduled(fixedRate = 5000L)
    public void handleInboxMessagesTask() {
        poll();
    }
    
    @Override
    public void poll() {
        
    }
}
