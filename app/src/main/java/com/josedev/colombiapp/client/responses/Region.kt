package com.josedev.colombiapp.client.responses



data class Region (
    val id: Long,
    val name: String,
    val description: String,
    val departments: Any? = null
)
