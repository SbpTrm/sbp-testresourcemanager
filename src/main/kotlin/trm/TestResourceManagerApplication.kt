package trm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
open class TestResourceManagerApplication {

}

fun main(args: Array<String>) {
    runApplication<TestResourceManagerApplication>(*args)
}