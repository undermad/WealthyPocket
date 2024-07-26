package wallet.persistence.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "wallet.persistence.repositories.read",
        entityManagerFactoryRef = "readEntityManagerFactoryWallet",
        transactionManagerRef = "readTransactionManagerWallet"
)
public class WalletReadPersistenceContextConfiguration {


    @Primary
    @Bean(name = "readEntityManagerFactoryWallet")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactoryWallet(
            @Qualifier("walletEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
            @Qualifier("walletDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {
        return builder.dataSource(dataSource)
                .packages("wallet.entities", "wallet.persistence.inbox", "wallet.persistence.outbox")
                .persistenceUnit("puReadWallet")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "readTransactionManagerWallet")
    public PlatformTransactionManager walletTransactionManager(
            @Qualifier("readEntityManagerFactoryWallet") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
