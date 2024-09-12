package ru.vddmit.task_db_01.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FlywayMigrationRunner {
    @Value("${spring.flyway.url}")
    private String url;

    @Value("${spring.flyway.user}")
    private String user;

    @Value("${spring.flyway.password}")
    private String password;

    @Value("${spring.flyway.locations}")
    private String[] locations;

    @Bean
    public CommandLineRunner runFlywayMigrations() {
        return args -> {
            Flyway flyway = Flyway.configure()
                    .dataSource(url, user, password)
                    .locations(locations)
                    .load();
            flyway.migrate();
        };
    }
}
