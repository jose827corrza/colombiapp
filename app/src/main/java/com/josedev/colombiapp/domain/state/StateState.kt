package com.josedev.colombiapp.domain.state

import com.josedev.colombiapp.client.responses.CityCapital

data class StateState(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val cityCapitalId: Long = 0,
    val municipalities: Long = 0,
    val surface: Long = 0,
    val population: Long = 0,
    val phonePrefix: String = "",
    val countryId: Long = 0,
    val cityCapital: CityCapital? = null,
    val country: Any? = null,
    val cities: Any? = null,
    val regionId: Long = 0,
    val region: Any? = null,
    val naturalAreas: Any? = null,
    val maps: Any? = null,
    val indigenousReservations: Any? = null,
    val airports: Any? = null
)
