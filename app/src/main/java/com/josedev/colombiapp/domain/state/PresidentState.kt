package com.josedev.colombiapp.domain.state

data class PresidentState(
    val id: Long = 0,
    val image: String = "",
    val name: String = "",
    val lastName: String = "",
    val startPeriodDate: String = "",
    val endPeriodDate: String? = "",
    val politicalParty: String = "",
    val description: String = "",
    val cityId: Long = 0,
    val city: Any? = null
)
