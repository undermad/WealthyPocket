package notification.persistence.configuration;

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
        basePackages = "notification.persistence.repositories.read",
        entityManagerFactoryRef = "readEntityManagerFactoryUserAccess",
        transactionManagerRef = "readTransactionManagerUserAccess"
)
public class NotificationReadPersistenceContextConfiguration {


    @Primary
    @Bean(name = "readEntityManagerFactoryNotification")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactoryUserAccess(
            @Qualifier("notificationEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
            @Qualifier("notificationDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {
        return builder.dataSource(dataSource)
                .packages("notification.entities", "notification.persistence.outbox")
                .persistenceUnit("puReadNotification")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "readTransactionManagerNotification")
    public PlatformTransactionManager notificationTransactionManager(
            @Qualifier("readEntityManagerFactoryNotification") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
