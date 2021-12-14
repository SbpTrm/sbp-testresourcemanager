package trm.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import trm.TestResourceManagerBot
import trm.controller.TestResourceController
import trm.core.UpdateHandler
import trm.repository.ResourceRepository
import javax.sql.DataSource

@Configuration
open class TrmBotAppConfig(@Autowired val dataSourceProperties: DataSourceProperties) {

    @Bean
    open fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = dataSourceProperties.url
        return HikariDataSource(config)
    }

    @Bean
    open fun jdbcTemplate(dataSource: DataSource): JdbcTemplate {
        val jdbcTemplate = JdbcTemplate(dataSource)
        println("jdbcTemplateBean=$jdbcTemplate")
        return jdbcTemplate
    }

    @Bean
    open fun resourceRepository(jdbcTemplate: JdbcTemplate): ResourceRepository {
        val resourceRepository = ResourceRepository(jdbcTemplate)
        println("resourceRepositoryBean=$resourceRepository")
        return resourceRepository
    }

    @Bean
    open fun updateHandler(resourceRepository: ResourceRepository): UpdateHandler {
        val updateHandler = UpdateHandler(resourceRepository)
        println("updateHandlerBean=$updateHandler")
        return updateHandler

    }

    @Bean
    open fun testResourceManagerBot(
        testResourceManagerConfigurationProperties: TestResourceManagerConfigurationProperties,
        updateHandler: UpdateHandler
    ): TestResourceManagerBot {
        val testResourceManagerBot = TestResourceManagerBot(testResourceManagerConfigurationProperties, updateHandler)
        println("testResourceManagerBean=$testResourceManagerBot")
        return testResourceManagerBot
    }

    @Bean
    open fun testResourceController(testResourceManagerBot: TestResourceManagerBot): TestResourceController {
        val testResourceController = TestResourceController(testResourceManagerBot)
        println("testResourceControllerBean=$testResourceController")
        return testResourceController
    }

}