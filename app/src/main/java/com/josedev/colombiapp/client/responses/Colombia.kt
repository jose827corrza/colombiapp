package com.josedev.colombiapp.client.responses

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
