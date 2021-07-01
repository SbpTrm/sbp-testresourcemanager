package ru.sbp.trm.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "test.resource.manager.bot")
public class TestResourceManagerConfigurationProperties {
    private String path;
    private String username;
    private String token;

    @PostConstruct
    void printConfig() {
        log.info("Using properties: " + this);
    }

}
