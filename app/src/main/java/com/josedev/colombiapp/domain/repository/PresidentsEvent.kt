package com.josedev.colombiapp.domain.repository

sealed interface PresidentsEvent {
    data object Presidents: PresidentsEvent
}