package com.josedev.colombiapp.domain.repository

sealed interface PresidentDetailEvent {
    data class PresidentDetail(val id: Long): PresidentDetailEvent

}