package com.josedev.colombiapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import com.josedev.colombiapp.domain.repository.StateDetailEvent
import com.josedev.colombiapp.domain.state.StateState
import com.josedev.colombiapp.presentation.StateDetailVM
import com.josedev.colombiapp.utils.Utils

@Composable
fun StateDetailScreen(
    navigation: NavHostController,
    id: String,
    modifier: Modifier = Modifier,
    vm: StateDetailVM = hiltViewModel()
) {

    val state by vm.state.collectAsState()
    val status by vm.status.collectAsState()

    LaunchedEffect(key1 = id) {
        vm.onEvent(StateDetailEvent.StateDetail(id.toLong()))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if(status.isLoading) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            StateDetail(state)
        }
    }
}

@Composable
fun StateDetail(
    state: StateState,
    modifier: Modifier = Modifier
) {
    Text(
        text = state.name,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
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
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
    ) {
        Text(text = "Municipalities:", fontWeight = FontWeight.Bold)
        Spacer(modifier = modifier.padding(25.dp))
        Text(text = state.municipalities.toString())
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
    ) {
        Text(text = "Surface:", fontWeight = FontWeight.Bold)
        Spacer(modifier = modifier.padding(25.dp))
        Text(text = Utils.formatter(state.surface))
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
    ) {
        Text(text = "Population:", fontWeight = FontWeight.Bold)
        Spacer(modifier = modifier.padding(25.dp))
        Text(text = Utils.formatter(state.surface))
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
    ) {
        Text(text = "Phone Prefix:", fontWeight = FontWeight.Bold)
        Spacer(modifier = modifier.padding(25.dp))
        Text(text = state.phonePrefix)
    }
    HorizontalDivider(modifier = modifier.padding(0.dp, 20.dp))
    Text(
        text = "Capital City",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth().padding(0.dp, 10.dp)
    )
    state.cityCapital?.let {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Capital:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.cityCapital.name)
        }
        Row(
            modifier = modifier.fillMaxWidth().padding(0.dp, 20.dp),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
//            Text(text = "Description:", fontWeight = FontWeight.Bold)
//            Spacer(modifier = modifier.padding(25.dp))
            Text(
                text = state.cityCapital.description,
                textAlign = TextAlign.Justify
            )
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Surface:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = Utils.formatter(state.cityCapital.surface))
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Population:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = Utils.formatter(state.cityCapital.population))
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Postal Code:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.cityCapital.postalCode)
        }
    }
}