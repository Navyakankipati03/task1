package com.example.configcheck;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfigValidator {

    private static final Logger log = LoggerFactory.getLogger(AppConfigValidator.class);

    @Value("${spring.datasource.url:#{null}}")
    private String dbUrl;

    @Value("${spring.datasource.username:#{null}}")
    private String dbUsername;

    @Value("${spring.datasource.password:#{null}}")
    private String dbPassword;

    @PostConstruct
    public void validate() {

        if (dbUrl == null || dbUrl.isBlank()) {
            log.error("CONFIG_MISSING: db.url is missing");
            throw new IllegalStateException("db.url missing");
        }

        if (dbUsername == null || dbUsername.isBlank()) {
            log.error("CONFIG_MISSING: db.username is missing");
            throw new IllegalStateException("db.username missing");
        }

        if (dbPassword == null || dbPassword.isBlank()) {
            log.error("CONFIG_MISSING: db.password is missing");
            throw new IllegalStateException("db.password missing");
        }

        log.info("All mandatory configs are present");
    }
}
