package nl.habiboellah.battleshiprest.config;

import nl.habiboellah.battleshiprest.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseConfig.class);

    @Autowired
    private ApplicationConfig config;

    @Bean
    CommandLineRunner populateDatabase(PlayerRepository repository) {
        return args -> {
            LOG.info("Starting with empty database");
        };
    }
}
