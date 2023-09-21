package nl.habiboellah.battleshiprest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static nl.habiboellah.battleshiprest.config.ApplicationConfig.Property.*;

@Component
public class ApplicationConfig {
    public enum Property {
        POPULATE_DB("database.populate");

        private final String key;

        Property(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    @Autowired
    private Environment env;

    public String getProperty(Property property) {
        return env.getRequiredProperty(property.getKey());
    }

    public boolean getPopulateDatabase() {
        return Boolean.parseBoolean(getProperty(POPULATE_DB));
    }
}
