package ectimel.commands.handlers;

import ectimel.commands.RegisterUser;
import ectimel.cqrs.CommandHandler;
import ectimel.entities.User;
import ectimel.exceptions.UserAlreadyExistException;
import ectimel.factories.AccountFactory;
import ectimel.repositories.UserRepository;
import ectimel.value_objects.Email;
import ectimel.value_objects.Password;


public class RegisterUserHandler implements CommandHandler<RegisterUser> {

    private final AccountFactory accountFactory;
    private final UserRepository userRepository;

    public RegisterUserHandler(AccountFactory accountFactory, UserRepository userRepository) {
        this.accountFactory = accountFactory;
        this.userRepository = userRepository;
    }

    @Override
    public void handleCommand(RegisterUser command) {
        Email email = new Email(command.email());
        Password password = new Password(command.encryptedPassword());
        
        User user = userRepository.getAsync(email).join();
        if(user != null) throw new UserAlreadyExistException(email);
        
        User newUser = accountFactory.createUser(email, password);
        userRepository.addAsync(newUser).join();
        
        
        
    }
}
