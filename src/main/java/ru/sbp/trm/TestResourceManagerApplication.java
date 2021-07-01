package ru.sbp.trm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.sbp.trm.config.TestResourceManagerConfigurationProperties;
import ru.sbp.trm.controller.TestResourceController;

@SpringBootApplication
@EnableConfigurationProperties
public class TestResourceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestResourceManagerApplication.class, args);
    }

    @Bean
    TestResourceManagerBot testResourceManagerBot(TestResourceManagerConfigurationProperties testResourceManagerConfigurationProperties){
        return new TestResourceManagerBot(testResourceManagerConfigurationProperties);
    }
}
