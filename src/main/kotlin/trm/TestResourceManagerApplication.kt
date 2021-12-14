package trm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import trm.config.TestResourceManagerConfigurationProperties
import trm.core.UpdateHandler

@SpringBootApplication
@EnableConfigurationProperties
open class TestResourceManagerApplication {

    @Bean
    open fun testResourceManagerBot(
        testResourceManagerConfigurationProperties: TestResourceManagerConfigurationProperties,
        updateHandler: UpdateHandler
    ) =
        TestResourceManagerBot(testResourceManagerConfigurationProperties, updateHandler)
}

fun main(args: Array<String>) {
    runApplication<TestResourceManagerApplication>(*args)
}