package com.josedev.colombiapp.domain.repository

sealed interface ColombiaEvent {
    data object Colombia: ColombiaEvent
}