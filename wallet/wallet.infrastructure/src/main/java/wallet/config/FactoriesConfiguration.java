package wallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wallet.factories.*;

@Configuration
public class FactoriesConfiguration {
    
    @Bean
    public MoneyFactory standardMoneyFactory() {
        return new StandardMoneyFactory();
    }
    
    @Bean
    public WalletFactory standardWalletFactory(MoneyFactory moneyFactory) {
        return new StandardWalletFactory(moneyFactory);
    }
    
    @Bean 
    public OwnerFactory standardOwnerFactory(WalletFactory walletFactory) {
        return new StandardOwnerFactory(walletFactory);
    }
}
