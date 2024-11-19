package com.josedev.colombiapp.domain.repository

sealed interface StateDetailEvent {
    data class StateDetail(val id: Long): StateDetailEvent
}