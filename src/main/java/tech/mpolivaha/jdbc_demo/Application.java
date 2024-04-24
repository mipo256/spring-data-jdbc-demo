package tech.mpolivaha.jdbc_demo;

import java.util.UUID;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import tech.mpolivaha.jdbc_demo.idgeneration.Article;

@SpringBootApplication
@EnableJdbcRepositories
@EnableJdbcAuditing
public class Application {

    @Bean(initMethod = "migrate")
    Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
            .locations("db.migrations")
            .dataSource(dataSource)
            .load();
    }

    @Bean
    BeforeSaveCallback<Article> articleCallback() {
        return (aggregate, aggregateChange) -> {
            if (aggregate.getId() == null) {
                aggregate.setId(UUID.randomUUID());
            }
            return aggregate;
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
