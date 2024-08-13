package wallet.commands.handlers;


import ectimel.cqrs.commands.CommandHandler;
import ectimel.cqrs.commands.Handler;
import org.springframework.transaction.annotation.Transactional;
import wallet.commands.CreateOwner;
import wallet.exceptions.OwnerAlreadyExist;
import wallet.factories.OwnerFactory;
import wallet.repositories.OwnerRepository;
import wallet.values.UserId;

@Handler
public class CreateOwnerHandler implements CommandHandler<CreateOwner> {
    
    private OwnerFactory ownerFactory;
    private OwnerRepository ownerRepository;

    public CreateOwnerHandler(OwnerFactory ownerFactory, OwnerRepository ownerRepository) {
        this.ownerFactory = ownerFactory;
        this.ownerRepository = ownerRepository;
    }

    @Transactional("writeTransactionManagerWallet")
    @Override
    public void handle(CreateOwner command) {
        var userId = new UserId(command.userId());
        var owner = ownerRepository.get(userId);
        if(owner != null) throw new OwnerAlreadyExist(owner.getId().id());
        
        var newOwner = ownerFactory.createOwner(command.userId());
        ownerRepository.addAsync(newOwner);
    }
}
