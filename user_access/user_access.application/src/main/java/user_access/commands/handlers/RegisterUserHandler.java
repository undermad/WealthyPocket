package user_access.commands.handlers;

import ectimel.outbox.OutboxMessage;
import ectimel.outbox.OutboxRepository;
import user_access.UserRegisteredEvent;
import user_access.commands.RegisterUser;
import ectimel.cqrs.commands.CommandHandler;
import ectimel.cqrs.commands.Handler;
import user_access.entities.User;
import user_access.exceptions.UserAlreadyExistException;
import user_access.factories.AccountFactory;
import user_access.policies.UserRegistrationData;
import user_access.repositories.UserRepository;
import user_access.value_objects.BornDate;
import user_access.value_objects.Country;
import user_access.value_objects.Email;
import user_access.value_objects.Password;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Handler
public class RegisterUserHandler implements CommandHandler<RegisterUser> {

    private final AccountFactory accountFactory;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final OutboxRepository<OutboxMessage> outboxRepository;

    public RegisterUserHandler(@Qualifier("userFactory") AccountFactory accountFactory,
                               UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               @Qualifier("userAccessOutbox") OutboxRepository outboxRepository) {
        this.accountFactory = accountFactory;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.outboxRepository = outboxRepository;
    }

    @Transactional("writeTransactionManagerUserAccess")
    @Override
    public void handle(RegisterUser command) {
        var email = new Email(command.email());

        User user = userRepository.get(email);
        if (user != null) throw new UserAlreadyExistException(email);

        var userRegistrationData = UserRegistrationData.builder()
                .email(email)
                .password(new Password(passwordEncoder.encode(command.password())))
                .country(Country.fromName(command.country()))
                .bornDate(new BornDate(command.bornDate()))
                .build();

        var newUser = accountFactory.createUser(userRegistrationData);

        userRepository.add(newUser);

        var event = UserRegisteredEvent.builder()
                .id(UUID.randomUUID())
                .userId(newUser.getId().id())
                .email(newUser.getEmail().value())
                .bornDate(newUser.getBornDate().value())
                .country(newUser.getCountry().value())
                .build();

        outboxRepository.saveMessage(event);
    }
}
