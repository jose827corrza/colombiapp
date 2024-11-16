package com.josedev.colombiapp.client.responses

import com.josedev.colombiapp.domain.state.ColombiaState

data class Colombia (
    val id: Long,
    val name: String,
    val description: String,
    val stateCapital: String,
    val surface: Long,
    val population: Long,
    val languages: List<String>,
    val timeZone: String,
    val currency: String,
    val currencyCode: String,
    val currencySymbol: String,
    val isoCode: String,
    val internetDomain: String,
    val phonePrefix: String,
    val radioPrefix: String,
    val aircraftPrefix: String,
    val subRegion: String,
    val region: String,
    val borders: List<String>,
    val flags: List<String>
)

fun Colombia.toColombiaState() = ColombiaState(
    id = id,
    name = name,
    description = description,
    stateCapital = stateCapital,
    surface = surface,
    population = population,
    languages = languages,
    timeZone = timeZone,
    currency = currency,
    currencyCode = currencyCode,
    currencySymbol = currencySymbol,
    isoCode = isoCode,
    internetDomain = internetDomain,
    phonePrefix = phonePrefix,
    radioPrefix = radioPrefix,
    aircraftPrefix = aircraftPrefix,
    subRegion = subRegion,
    region = region,
    borders = borders,
    flags = flags
)
