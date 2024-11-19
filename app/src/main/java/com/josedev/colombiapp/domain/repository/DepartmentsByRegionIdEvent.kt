package com.josedev.colombiapp.domain.repository

sealed interface DepartmentsByRegionIdEvent {
    data class DepartmentsByIdRegion(val id: Long): DepartmentsByRegionIdEvent
}