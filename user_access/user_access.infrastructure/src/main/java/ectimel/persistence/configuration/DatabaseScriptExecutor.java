package ectimel.persistence.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("userAccessDatabaseScriptExecutor")
public class DatabaseScriptExecutor {

    private final DataSource dataSource;

    public DatabaseScriptExecutor(@Qualifier("userAccessDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void executeScripts() {
        try {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("user-access-schema.sql"));
            populator.execute(dataSource);
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute database script", e);
        }
    }
}
