package com.josedev.colombiapp.client.responses

data class Department (
    val id: Long,
    val name: String,
    val description: String,
    val cityCapitalID: Long,
    val municipalities: Long,
    val surface: Long,
    val population: Long,
    val phonePrefix: String,
    val countryID: Long,
    val cityCapital: CityCapital,
    val country: Any? = null,
    val cities: Any? = null,
    val regionID: Long,
    val region: Any? = null,
    val naturalAreas: Any? = null,
    val maps: Any? = null,
    val indigenousReservations: Any? = null,
    val airports: Any? = null
)
