package com.josedev.colombiapp.domain.state

import com.google.firebase.analytics.FirebaseAnalytics

data class StatusState(
    var isLoading: Boolean = false,
    var isError: Boolean = false,
    var error: String? = null,
)
