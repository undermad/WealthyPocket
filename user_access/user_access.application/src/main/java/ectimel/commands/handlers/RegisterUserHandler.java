package ectimel.commands.handlers;

import ectimel.commands.RegisterUser;
import ectimel.cqrs.commands.CommandHandler;
import ectimel.cqrs.commands.Handler;
import ectimel.entities.User;
import ectimel.exceptions.UserAlreadyExistException;
import ectimel.factories.AccountFactory;
import ectimel.repositories.UserRepository;
import ectimel.value_objects.Email;
import ectimel.value_objects.Password;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;


@Handler
public class RegisterUserHandler implements CommandHandler<RegisterUser> {

    private final AccountFactory accountFactory;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserHandler(@Qualifier("userFactory") AccountFactory accountFactory, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accountFactory = accountFactory;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void handleCommand(RegisterUser command) {
        Email email = new Email(command.email());
        Password password = new Password(passwordEncoder.encode(command.password()));

        User user = userRepository.getAsync(email).join();
        if (user != null) {
            throw new UserAlreadyExistException(email);
        }

        User newUser = accountFactory.createUser(email, password);
        userRepository.addAsync(newUser).join();

    }
}
