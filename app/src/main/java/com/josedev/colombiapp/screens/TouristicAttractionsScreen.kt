package com.josedev.colombiapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import com.josedev.colombiapp.components.PreviewCard
import com.josedev.colombiapp.components.TrackScreen
import com.josedev.colombiapp.navigation.routes.AppRoute
import com.josedev.colombiapp.presentation.TouristicAttractionsVM

@Composable
fun TouristicAttractionsScreen(
    analytics: FirebaseAnalytics,
    navigation: NavHostController,
    modifier: Modifier = Modifier,
    vm: TouristicAttractionsVM = hiltViewModel()
    ) {

    val state by vm.state.collectAsState()
    val status by vm.status.collectAsState()

    TrackScreen(name = "touristic-attraction", analytics = analytics)

    if(status.isLoading){
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Log.d("RegionsScreen", "Circular: $state")
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(state){
                PreviewCard(
                    startPeriod = "",
                    endPeriod = "",
                    title = it.name,
                    image = it.images[0],
                    goTo = { goToTouristicADetail(navigation, it.id) }
                )
            }
        }
    }
}

fun goToTouristicADetail(navigation: NavHostController, id: Long){
    navigation.navigate(AppRoute.TouristicAttractionsById(id).route)
}