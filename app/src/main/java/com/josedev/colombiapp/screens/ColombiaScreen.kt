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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.google.firebase.analytics.FirebaseAnalytics
import com.josedev.colombiapp.components.MakeBulletedList
import com.josedev.colombiapp.components.TrackScreen
import com.josedev.colombiapp.domain.state.ColombiaState
import com.josedev.colombiapp.presentation.ColombiaVM
import com.josedev.colombiapp.utils.Utils

@Composable
fun ColombiaScreen(
    analytics: FirebaseAnalytics,
    modifier: Modifier = Modifier,
    vm: ColombiaVM = hiltViewModel()
) {

    val state by vm.state.collectAsState()
    val status by vm.status.collectAsState()

    TrackScreen(name = "colombia", analytics = analytics)

    Column(modifier = modifier.fillMaxSize()) {
        if (status.isLoading){
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            ColombiaInfo(state)
        }
    }
}

@Composable
fun ColombiaInfo(
    state: ColombiaState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            text = state.name,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = state.description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "State Capital", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.stateCapital)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Surface", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = Utils.formatter(state.surface))
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Population", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = Utils.formatter(state.population))
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Languages", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = MakeBulletedList(items = state.languages))
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Time Zone", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.timeZone)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Currency Code", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.currency)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "ISO Code:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.isoCode)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Internet Domain:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.internetDomain)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Phone Prefix:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.phonePrefix)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Radio Prefix:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.radioPrefix)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Aircraft Prefix:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.aircraftPrefix)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Region:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.region)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Sub Region:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = state.subRegion)
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Borders:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            Text(text = MakeBulletedList(state.borders))
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.aligned(Alignment.CenterHorizontally)
        ) {
            Text(text = "Flag:", fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.padding(25.dp))
            state.flags.forEach {
                AsyncImage(model = it, contentDescription = "Flag")
            }
        }
    }
}
