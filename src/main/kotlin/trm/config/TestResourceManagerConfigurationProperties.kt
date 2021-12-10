package trm.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "test.resource.manager.bot")
data class TestResourceManagerConfigurationProperties(
    var path: String = "",
    var username: String = "",
    var token: String = ""
)