package com.josedev.colombiapp.domain.state

data class ColombiaState(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val stateCapital: String = "",
    val surface: Long = 0,
    val population: Long = 0,
    val languages: List<String> = emptyList(),
    val timeZone: String = "",
    val currency: String = "",
    val currencyCode: String = "",
    val currencySymbol: String = "",
    val isoCode: String = "",
    val internetDomain: String = "",
    val phonePrefix: String = "",
    val radioPrefix: String = "",
    val aircraftPrefix: String = "",
    val subRegion: String = "",
    val region: String = "",
    val borders: List<String> = emptyList(),
    val flags: List<String> = emptyList()
)
