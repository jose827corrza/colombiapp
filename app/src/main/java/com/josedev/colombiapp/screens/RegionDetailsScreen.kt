package com.josedev.colombiapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.josedev.colombiapp.domain.state.RegionState
import com.josedev.colombiapp.navigation.routes.AppRoute
import com.josedev.colombiapp.presentation.DepartmentsByRegionId

@Composable
fun RegionDetailsScreen(
    navigation: NavHostController,
    id: String,
    modifier: Modifier = Modifier,
    vm: DepartmentsByRegionId = hiltViewModel()
) {

    val state by vm.state.collectAsState()
    val status by vm.status.collectAsState()

    LaunchedEffect(key1 = id) {
        vm.onEvent(DepartmentsByRegionIdEvent.DepartmentsByIdRegion(id.toLong()))
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        if(status.isLoading) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            RegionInfo(state, navigation)
        }
    }
}

@Composable
fun RegionInfo(
    state: RegionState,
    navigation: NavHostController,
    modifier: Modifier = Modifier
) {
    Text(
        text = state.name,
        modifier = modifier
            .fillMaxWidth(),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = modifier.padding(0.dp, 20.dp))
    Text(
        text = state.description,
        modifier = modifier
            .fillMaxWidth(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Justify
    )
    Spacer(modifier = modifier.padding(0.dp, 15.dp))
    Text(
        text = "States:",
        textAlign = TextAlign.Left,
        fontSize = 28.sp,
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space = 5.dp),
    ) {
        state.departments?.let {
            items(state.departments){
                PreviewCard(title = it.name, goTo = { goToStateDetails(it.id, navigation) })
            }
        }
    }
}

fun goToStateDetails(id: Long, navigation: NavHostController) {
    navigation.navigate(AppRoute.StateDetailById(id).route)
}