package com.josedev.colombiapp.domain.repository

sealed interface TouristicAttractionsEvent {
    data object TouristicAttractions : TouristicAttractionsEvent
}