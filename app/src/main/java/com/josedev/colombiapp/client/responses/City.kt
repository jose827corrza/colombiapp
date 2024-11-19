package com.josedev.colombiapp.client.responses

import com.josedev.colombiapp.domain.state.CityState

data class City (
    val id: Long,
    val name: String,
    val description: String,
    val surface: Long,
    val population: Long,
    val postalCode: String,
    val departmentId: Long,
    val department: Any? = null,
    val touristAttractions: Any? = null,
    val presidents: List<Any?>,
    val indigenousReservations: Any? = null,
    val airports: Any? = null,
    val radios: Any? = null
)

fun City.toCityState() = CityState(
    id = id,
    name = name,
    description = description,
    surface = surface,
    population = population,
    postalCode = postalCode,
    departmentId = departmentId,
    department = department,
    touristAttractions = touristAttractions,
    presidents = presidents,
    indigenousReservations = indigenousReservations,
    airports = airports,
    radios = radios
)