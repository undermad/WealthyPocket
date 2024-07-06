package ectimel.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = "ectimel",
//        entityManagerFactoryRef = "user_accessEntityManager",
//        transactionManagerRef = "user_accessTransactionManager"
//)
public class DatabaseConfiguration {

//    @Bean(name = "user_accessDataSource")
//    @ConfigurationProperties(prefix = "useraccess.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "user_accessEntityManager")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            @Qualifier("user_accessDataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("ectimel");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        return em;
//    }
//
//    @Bean(name = "user_accessTransactionManager")
//    public JpaTransactionManager transactionManager(
//            @Qualifier("user_accessEntityManager") LocalContainerEntityManagerFactoryBean module1EntityManager) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(module1EntityManager.getObject());
//        return transactionManager;
//    }
}