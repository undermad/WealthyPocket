package ectimel.commands.handlers;

import ectimel.commands.Authenticate;
import ectimel.cqrs.commands.CommandHandler;
import ectimel.cqrs.commands.Handler;
import ectimel.repositories.UserRepository;
import ectimel.value_objects.Email;
import ectimel.value_objects.Password;
import org.springframework.security.crypto.password.PasswordEncoder;

@Handler
public class AuthenticateHandler implements CommandHandler<Authenticate> {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticateHandler(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void handleCommand(Authenticate command) {
        
        var user = repository.getAsync(new Email(command.email())).join();
        if(user.validatePassword(new Password(passwordEncoder.encode(command.password())))) {
            
        }
        
        
    }
}
