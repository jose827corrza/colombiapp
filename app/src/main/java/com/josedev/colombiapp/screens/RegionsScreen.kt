package com.josedev.colombiapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.josedev.colombiapp.components.PreviewCard
import com.josedev.colombiapp.domain.repository.DepartmentsByRegionIdEvent
import com.josedev.colombiapp.domain.repository.RegionsEvent
import com.josedev.colombiapp.domain.state.RegionState
import com.josedev.colombiapp.navigation.routes.AppRoute
import com.josedev.colombiapp.presentation.DepartmentsByRegionId
import com.josedev.colombiapp.presentation.RegionsVM

@Composable
fun RegionsScreen(
    navigation: NavHostController,
    modifier: Modifier = Modifier,
    vm: RegionsVM = hiltViewModel(),
) {

    val state by vm.state.collectAsState()
    val status by vm.status.collectAsState()


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if(status.isLoading){
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Log.d("RegionsScreen", "Circular: $state")
                CircularProgressIndicator()
            }
        } else {
            Text(
                text = "Regions",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
                )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(space = 5.dp),
            ) {
                items(state){
                    PreviewCard(title = it.name, goTo = { navToDetailsFun(it.id, navigation) })
                }
            }
        }
    }
}

fun navToDetailsFun(id: Long, nav: NavHostController){
    nav.navigate(AppRoute.DepartmentsByRegionId(id).route)
}