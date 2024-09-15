package user_access.persistence.configuration;

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
        basePackages = "user_access.persistence.repositories.read",
        entityManagerFactoryRef = "readEntityManagerFactoryUserAccess",
        transactionManagerRef = "readTransactionManagerUserAccess"
)
public class UserAccessReadPersistenceContextConfiguration {


    @Primary
    @Bean(name = "readEntityManagerFactoryUserAccess")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactoryUserAccess(
            @Qualifier("userAccessEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
            @Qualifier("userAccessDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {
        return builder.dataSource(dataSource)
                .packages("user_access.entities", "user_access.persistence")
                .persistenceUnit("puReadUserAccess")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "readTransactionManagerUserAccess")
    public PlatformTransactionManager userAccessTransactionManager(
            @Qualifier("readEntityManagerFactoryUserAccess") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
