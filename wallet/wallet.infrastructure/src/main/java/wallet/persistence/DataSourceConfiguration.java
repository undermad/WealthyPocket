package wallet.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration("walletDataSourceConfiguration")
public class DataSourceConfiguration {

    @Value("${wallet-postgresql-host}")
    private String postgresHost;

    @Value("${wallet-postgresql-port}")
    private String postgresPort;

    @Value("${wallet-postgresql-usernmad}")
    private String postgresUsername;

    @Value("${wallet-postgresql-password}")
    private String postgresPassword;

    @Bean(name = "walletDataSource")
    public DataSource userAccessDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://" + postgresHost + ":" + postgresPort + "/wallet");
        config.setUsername(postgresUsername);
        config.setPassword(postgresPassword);
        return new HikariDataSource(config);
    }

    @Bean("walletEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(JpaProperties jpaProperties) {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(),
                jpaProperties.getProperties(),
                null
        );
    }
}
