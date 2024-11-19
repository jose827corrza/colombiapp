package com.josedev.colombiapp.domain.repository

sealed interface TouristicADetailEvent {
    data class TouristicADetail(val id: Long): TouristicADetailEvent
}