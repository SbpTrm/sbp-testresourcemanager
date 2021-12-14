package trm.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
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
    open fun jdbcTemplate(dataSource: DataSource) = JdbcTemplate(dataSource)

    @Bean
    open fun resourceRepository(jdbcTemplate: JdbcTemplate) = ResourceRepository(jdbcTemplate)

    @Bean
    open fun updateHandler(resourceRepository: ResourceRepository) = UpdateHandler(resourceRepository)

}