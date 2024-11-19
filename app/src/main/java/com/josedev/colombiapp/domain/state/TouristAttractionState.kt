package com.josedev.colombiapp.domain.state

import com.josedev.colombiapp.client.responses.City

data class TouristAttractionState(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val images: List<String> = emptyList(),
    val latitude: String = "",
    val longitude: String = "",
    val cityId: Long = 0,
    val city: City? = null
)
