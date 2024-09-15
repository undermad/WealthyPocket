package notification.persistence.configuration;


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


// Use @PersistenceUnit to inject EntityManagerFactory
// You need to manually handle entityManager creation
// You can control transactions (entityManager.getTransaction().begin() -> entityManager.getTransaction().commit() -> 
// entityManager.getTransaction().close(); )

// Use @PersistenceContext to inject EntityManager and handle transaction automatically
// You can utilize @Transaction easily 



@Configuration
@EnableJpaRepositories(
        basePackages = "notification.persistence.repositories.write",
        entityManagerFactoryRef = "writeEntityManagerFactoryUserAccess",
        transactionManagerRef = "writeTransactionManagerUserAccess"
)
public class NotificationWritePersistenceContextConfiguration {

    @Bean(name = "writeEntityManagerFactoryNotification")
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactoryNotification(
            @Qualifier("notificationEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
            @Qualifier("notificationDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {
                return builder.dataSource(dataSource)
                        .packages("notification.entities", "notification.persistence")
                        .persistenceUnit("puWriteNotification")
                        .properties(jpaProperties.getProperties())
                        .build();
    }

    @Bean(name = "writeTransactionManagerNotification")
    public PlatformTransactionManager notificationTransactionManager(
            @Qualifier("writeEntityManagerFactoryNotification") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
