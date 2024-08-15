package user_access.persistence.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration("userAccessDataSourceConfiguration")
public class DataSourceConfiguration {

    @Value("${user.access-postgresql-host}")
    private String postgresHost;

    @Value("${user.access-postgresql-port}")
    private String postgresPort;

    @Value("${user.access-postgresql-usernmad}")
    private String postgresUsername;

    @Value("${user.access-postgresql-password}")
    private String postgresPassword;

    @Bean(name = "userAccessDataSource")
    public DataSource userAccessDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://" + postgresHost + ":" + postgresPort + "/user_access");
        config.setUsername(postgresUsername);
        config.setPassword(postgresPassword);
        return new HikariDataSource(config);
    }

    @Bean("userAccessEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(JpaProperties jpaProperties) {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(),
                jpaProperties.getProperties(),
                null
        );
    }
}
