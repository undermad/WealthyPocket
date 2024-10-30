package wallet.commands.handlers;


import wallet.cqrs.commands.CommandHandler;
import wallet.cqrs.commands.Handler;
import org.springframework.transaction.annotation.Transactional;
import wallet.commands.CreateOwner;
import wallet.exceptions.OwnerAlreadyExist;
import wallet.factories.OwnerFactory;
import wallet.policies.WalletPolicyData;
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
        if(owner.isPresent()) throw  new OwnerAlreadyExist("Owner already exist.");
        
        var walletPolicyData = new WalletPolicyData(userId, command.country());
        
        var newOwner = ownerFactory.createOwner(walletPolicyData);
        ownerRepository.addAsync(newOwner);
    }
}
