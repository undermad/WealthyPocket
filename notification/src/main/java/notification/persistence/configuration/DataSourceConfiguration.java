package notification.persistence.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration("notificationDataSourceConfiguration")
public class DataSourceConfiguration {

    @Value("${user.access-postgresql-host}")
    private String postgresHost;

    @Value("${user.access-postgresql-port}")
    private String postgresPort;

    @Value("${user.access-postgresql-username}")
    private String postgresUsername;

    @Value("${user.access-postgresql-password}")
    private String postgresPassword;

    @Bean(name = "notificationDataSource")
    public DataSource notificationDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://" + postgresHost + ":" + postgresPort + "/notification");
        config.setUsername(postgresUsername);
        config.setPassword(postgresPassword);
        return new HikariDataSource(config);
    }

    @Bean("notificationEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(JpaProperties jpaProperties) {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(),
                jpaProperties.getProperties(),
                null
        );
    }
}
