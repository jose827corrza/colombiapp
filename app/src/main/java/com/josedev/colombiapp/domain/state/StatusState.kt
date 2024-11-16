package com.josedev.colombiapp.domain.state

data class StatusState(
    var isLoading: Boolean = false,
    var isError: Boolean = false,
    var error: String? = null
)
