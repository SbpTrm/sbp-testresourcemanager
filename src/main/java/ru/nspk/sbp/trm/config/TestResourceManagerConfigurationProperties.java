package ru.nspk.sbp.trm.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
x@Data
@Slf4j
@ConfigurationProperties(prefix = "test.resource.manager.bot")
public class TestResourceManagerConfigurationProperties {
    @Value("${path}")
    private String botPath;
    @Value("${username}")
    private String botUsername;
    @Value("${token}")
    private String botToken;

    @PostConstruct
    void printConfig() {
        log.info("Using properties: " + this);
    }

}
