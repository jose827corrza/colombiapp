package com.josedev.colombiapp.client.responses

import com.josedev.colombiapp.domain.state.RegionState


data class Region (
    val id: Long,
    val name: String,
    val description: String,
    val departments: List<Department>? = null
)

fun Region.toRegionState() = RegionState(
    id = id,
    name = name,
    description = description,
    departments = departments
)
