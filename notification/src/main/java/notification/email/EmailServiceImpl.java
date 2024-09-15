package notification.email;

import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
    
    @Override
    public void sendEmail(EmailData emailData) {
        System.out.println("sending an email");
    }
}
