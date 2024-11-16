package com.josedev.colombiapp.client.responses

import com.josedev.colombiapp.domain.state.StateState

data class Department (
    val id: Long,
    val name: String,
    val description: String,
    val cityCapitalId: Long,
    val municipalities: Long,
    val surface: Long,
    val population: Long,
    val phonePrefix: String,
    val countryId: Long,
    val cityCapital: CityCapital,
    val country: Any? = null,
    val cities: Any? = null,
    val regionId: Long,
    val region: Any? = null,
    val naturalAreas: Any? = null,
    val maps: Any? = null,
    val indigenousReservations: Any? = null,
    val airports: Any? = null
)

fun Department.toStateState() = StateState(
    id = id,
    name = name,
    description = description,
    cityCapitalId = cityCapitalId,
    municipalities = municipalities,
    surface = surface,
    population = population,
    phonePrefix = phonePrefix,
    countryId = countryId,
    cityCapital = cityCapital,
    country = country,
    cities = cities,
    regionId = regionId,
    region = region,
    naturalAreas = naturalAreas,
    maps = maps,
    indigenousReservations = indigenousReservations,
    airports = airports
)
