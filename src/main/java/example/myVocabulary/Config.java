package example.myVocabulary;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.sql.DataSource;
import java.io.File;

@Configuration
@RequiredArgsConstructor
public class Config implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    private final DataSource dataSource;

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.addErrorPages(
                new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
    }

    @PostConstruct
    public void migrateDatabase() {
        if (!databaseFileExists()) {
            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();
        }
    }

    private boolean databaseFileExists() {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current Directory: " + currentDirectory);
        File databaseFile = new File(currentDirectory, "database.mv.db");
        return databaseFile.exists();
    }
}