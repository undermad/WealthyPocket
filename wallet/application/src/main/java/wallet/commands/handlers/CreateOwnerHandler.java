package wallet.commands.handlers;


import ectimel.cqrs.commands.CommandHandler;
import ectimel.cqrs.commands.Handler;
import wallet.commands.CreateOwner;
import wallet.factories.OwnerFactory;

@Handler
public class CreateOwnerHandler implements CommandHandler<CreateOwner> {
    
    private OwnerFactory ownerFactory;

    public CreateOwnerHandler(OwnerFactory ownerFactory) {
        this.ownerFactory = ownerFactory;
    }

    @Override
    public void handle(CreateOwner command) {
        var owner = ownerFactory.createOwner(command.userId());

        
    }
}
