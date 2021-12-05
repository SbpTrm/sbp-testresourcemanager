package trm.core

import org.springframework.stereotype.Service

data class ResourceData(
    val name: String,
    val status: ResourceStatus = ResourceStatus.FREE,
    val userId: String? = null,
    val date: String? = null
)

enum class ResourceStatus { FREE, RESERVED }

@Service
object ResourceRepository {
    private val resources = mutableSetOf<ResourceData>()

    init {
        resources.add(ResourceData(name = "int-1", status = ResourceStatus.FREE, userId = null, date = null))
        resources.add(ResourceData(name = "int-2", status = ResourceStatus.FREE, userId = null, date = null))
        resources.add(ResourceData(name = "int-3", status = ResourceStatus.FREE, userId = null, date = null))
        resources.add(ResourceData(name = "int-4", status = ResourceStatus.FREE, userId = null, date = null))
    }

    fun getFreeResources(): List<ResourceData> =
        resources.filter { resource -> resource.status == ResourceStatus.FREE }

    fun getReservedResources(userId: String): List<ResourceData> =
        resources.filter { resource -> resource.status == ResourceStatus.RESERVED && resource.userId == userId }

    fun orderResource(userId: String, resourceName: String) {
        val freeResource =
            resources.find { resource -> resource.status == ResourceStatus.FREE && resource.name == resourceName }
        freeResource?.let { resource ->
            resources.remove(resource)
            resources.add(ResourceData(name = resource.name, status = ResourceStatus.RESERVED, userId = userId))
        }
    }

    fun dismissResource(userId: String, resourceName: String) {
        val reservedResource =
            resources.find { resource -> resource.status == ResourceStatus.RESERVED && resource.name == resourceName && resource.userId == userId }
        reservedResource?.let { resource ->
            resources.remove(resource)
            resources.add(ResourceData(name = resource.name, status = ResourceStatus.FREE))
        }
    }
}