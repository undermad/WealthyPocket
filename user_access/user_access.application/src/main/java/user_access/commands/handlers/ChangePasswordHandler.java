package user_access.commands.handlers;

import wallet.cqrs.commands.CommandHandler;
import wallet.cqrs.commands.Handler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import user_access.commands.ChangePassword;
import user_access.exceptions.UserNotFoundException;
import user_access.repositories.UserRepository;
import user_access.value_objects.Password;
import user_access.value_objects.UserId;

@Handler
public class ChangePasswordHandler implements CommandHandler<ChangePassword> {
    
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public ChangePasswordHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional("writeTransactionManagerUserAccess")
    @Override
    public void handle(ChangePassword command) {
        
        var user = userRepository.get(new UserId(command.userId()));
        
        if(user == null) throw new UserNotFoundException("User not found.");
        
        var encodedNewPassword = new Password(passwordEncoder.encode(command.newPassword()));
        
        user.changePassword(encodedNewPassword);
        
        userRepository.update(user);
    }
}
