package com.josedev.colombiapp.domain.state

data class CityState(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val surface: Long = 0,
    val population: Long = 0,
    val postalCode: String = "",
    val departmentId: Long = 0,
    val department: Any? = null,
    val touristAttractions: Any? = null,
    val presidents: List<Any?> = emptyList(),
    val indigenousReservations: Any? = null,
    val airports: Any? = null,
    val radios: Any? = null
)
