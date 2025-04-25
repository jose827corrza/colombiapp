package com.josedev.colombiapp.permissions

interface PermissionsProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}