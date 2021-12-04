package trm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import trm.config.TestResourceManagerConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties
open class TestResourceManagerApplication {

    @JvmName("main")
    fun main(args: Array<String>) = runApplication<TestResourceManagerApplication>(*args)

    @Bean
    open fun testResourceManagerBot(testResourceManagerConfigurationProperties: TestResourceManagerConfigurationProperties) =
        TestResourceManagerBot(testResourceManagerConfigurationProperties)
}