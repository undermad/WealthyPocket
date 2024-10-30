package notification.handlers;

import wallet.message_broker.EventController;
import wallet.message_broker.EventListener;
import notification.email.EmailData;
import notification.email.EmailService;
import user_access.UserRegisteredEvent;

@EventController(inboxRepositoryBean = "notificationInboxRepository")
public class NotificationEventHandlers {
    
    private EmailService emailService;

    public NotificationEventHandlers(EmailService emailService) {
        this.emailService = emailService;
    }


    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        emailService.sendEmail(new EmailData("destination"));
    }
    
    
    
}
