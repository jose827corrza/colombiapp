package com.josedev.colombiapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.josedev.colombiapp.components.PreviewCard
import com.josedev.colombiapp.navigation.routes.AppRoute
import com.josedev.colombiapp.presentation.PresidentsVM

@Composable
fun PresidentsScreen(
    navigation: NavHostController,
    modifier: Modifier = Modifier,
    vm: PresidentsVM = hiltViewModel()
) {

    val state by vm.state.collectAsState()
    val status by vm.status.collectAsState()

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
                    startPeriod = it.startPeriodDate,
                    endPeriod = it.endPeriodDate,
                    title = "${it.name} ${it.lastName}",
                    image = it.image,
                    goTo = { test(navigation, it.id) }
                )
            }
        }
    }
}

fun test(navigation: NavHostController, id: Long){
    navigation.navigate(AppRoute.PresidentDetailById(id).route)
}