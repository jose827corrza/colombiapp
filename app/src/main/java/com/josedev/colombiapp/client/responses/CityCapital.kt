package com.josedev.colombiapp.client.responses

data class CityCapital (
    val id: Long,
    val name: String,
    val description: String,
    val surface: Long,
    val population: Long,
    val postalCode: String,
    val departmentID: Long,
    val department: Any? = null,
    val touristAttractions: Any? = null,
    val presidents: Any? = null,
    val indigenousReservations: Any? = null,
    val airports: Any? = null,
    val radios: Any? = null
)
