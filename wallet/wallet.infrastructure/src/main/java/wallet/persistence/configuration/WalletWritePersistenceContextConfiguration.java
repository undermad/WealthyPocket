package wallet.persistence.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(
        basePackages = "wallet.persistence.repositories.write",
        entityManagerFactoryRef = "writeEntityManagerFactoryWallet",
        transactionManagerRef = "writeTransactionManagerWallet"
)
public class WalletWritePersistenceContextConfiguration {

    @Bean(name = "writeEntityManagerFactoryWallet")
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactoryWallet(
            @Qualifier("walletEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
            @Qualifier("walletDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {
        return builder.dataSource(dataSource)
                .packages("wallet.entities", "wallet.persistence.inbox", "wallet.persistence.outbox")
                .persistenceUnit("puWriteWallet")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "writeTransactionManagerWallet")
    public PlatformTransactionManager walletTransactionManager(
            @Qualifier("writeEntityManagerFactoryWallet") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
    
}
