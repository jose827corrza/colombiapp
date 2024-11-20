package com.josedev.colombiapp.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

@Composable
fun TrackScreen(
    name: String,
    analytics: FirebaseAnalytics,
    modifier: Modifier = Modifier) {
    DisposableEffect(Unit) {
        onDispose {
            Log.d("Analytics", "Screen: $name")
            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, name)
            }
        }
    }
}