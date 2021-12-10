package trm.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.stereotype.Component

@Component
class DataSourceConfigInfo : InfoContributor {
    @Autowired
    lateinit var dataSourceProperties: DataSourceProperties

    override fun contribute(builder: Info.Builder) {
        builder.withDetail(
            "dataSourceProperties", mapOf(
                "url" to dataSourceProperties.url,
                "driver" to dataSourceProperties.driverClassName
            )
        )
    }
}