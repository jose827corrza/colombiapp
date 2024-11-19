package com.josedev.colombiapp.client.responses

import com.josedev.colombiapp.domain.state.PresidentState

data class President(
    val id: Long,
    val image: String,
    val name: String,
    val lastName: String,
    val startPeriodDate: String,
    val endPeriodDate: String,
    val politicalParty: String,
    val description: String,
    val cityId: Long,
    val city: Any? = null
)

fun President.toPresidentState() = PresidentState(
    id = id,
    image = image,
    name = name,
    lastName = lastName,
    startPeriodDate = startPeriodDate,
    endPeriodDate = endPeriodDate,
    politicalParty = politicalParty,
    description = description,
    cityId = cityId,
    city = city
)
