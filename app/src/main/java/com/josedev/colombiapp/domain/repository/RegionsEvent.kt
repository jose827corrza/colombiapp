package com.josedev.colombiapp.domain.repository

sealed interface RegionsEvent {
    data object Regions: RegionsEvent
}