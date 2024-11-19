package com.josedev.colombiapp.client.responses

import com.josedev.colombiapp.domain.state.TouristAttractionState

data class TouristAttraction(
    val id: Long,
    val name: String,
    val description: String,
    val images: List<String>,
    val latitude: String,
    val longitude: String,
    val cityId: Long,
    val city: City
)

fun TouristAttraction.toTouristAttractionState() = TouristAttractionState(
    id = id,
    name = name,
    description = description,
    images = images,
    latitude = latitude,
    longitude = longitude,
    cityId = cityId,
    city = city
)
