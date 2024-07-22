package ectimel.persistence;

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
        basePackages = "ectimel.persistence.repositories.read",
        entityManagerFactoryRef = "readEntityManagerFactoryWallet",
        transactionManagerRef = "readTransactionManagerWallet"
)
public class ReadWalletPersistenceContextConfiguration {


    @Primary
    @Bean(name = "readEntityManagerFactoryWallet")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactoryUserAccess(
            EntityManagerFactoryBuilder builder, @Qualifier("walletDataSource") DataSource dataSource, JpaProperties jpaProperties) {
        return builder.dataSource(dataSource)
                .packages("ectimel.entities")
                .persistenceUnit("puReadWallet")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "readTransactionManagerWallet")
    public PlatformTransactionManager transactionManager(
            @Qualifier("readEntityManagerFactoryWallet") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
