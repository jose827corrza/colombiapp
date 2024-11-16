package com.josedev.colombiapp.domain.state

import com.josedev.colombiapp.client.responses.Department

data class RegionState(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val departments: List<Department>? = null
)
