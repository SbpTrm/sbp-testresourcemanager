package trm.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource

data class ResourceData(
    val name: String,
    val status: ResourceStatus = ResourceStatus.FREE,
    val userId: String? = null,
    val userName: String? = null,
    val timestamp: String? = null
)

enum class ResourceStatus { FREE, RESERVED }

open class ResourceRepository(private val dataSource: DataSource) {

    private val jdbcTemplate: JdbcTemplate

    init {
        this.jdbcTemplate = JdbcTemplate(dataSource)
    }

    fun getFreeResources(): List<ResourceData> {
        println("resourceJdbcTemplate=$jdbcTemplate")
        return jdbcTemplate.query(
            "select * from resources where status = '${ResourceStatus.FREE.name}' order by name"
        )
        { rs, _ ->
            ResourceData(
                name = rs.getString("name"),
                status = ResourceStatus.valueOf(rs.getString("status")),
                userId = rs.getNString("userId"),
                userName = rs.getNString("userName"),
                timestamp = rs.getNString("timestamp")
            )
        }
    }

    fun getReservedResourcesByUserId(userId: String): List<ResourceData> {
        return jdbcTemplate.query(
            "select * from resources where status = '${ResourceStatus.RESERVED.name}' and userId = '$userId' order by name"
        )
        { rs, _ ->
            ResourceData(
                name = rs.getString("name"),
                status = ResourceStatus.valueOf(rs.getString("status")),
                userId = rs.getNString("userId"),
                userName = rs.getNString("userId"),
                timestamp = rs.getNString("userId")
            )
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    open fun orderResource(userId: String, resourceName: String) {
        jdbcTemplate.update(
            """
            UPDATE resources
            SET status = '${ResourceStatus.RESERVED.name}',
                userId = '$userId'
            WHERE name = '$resourceName' and status = '${ResourceStatus.FREE.name}; 
            """, mutableMapOf<String, String>()
        )
    }

    fun dismissResource(userId: String, resourceName: String) {
        jdbcTemplate.update(
            """
            UPDATE resources
            SET status = '${ResourceStatus.FREE.name}',
                userId = '$userId'
            WHERE name = '$resourceName' and status = '${ResourceStatus.RESERVED.name}' and userId = '$userId'
            """, mutableMapOf<String, String>()
        )
    }
}