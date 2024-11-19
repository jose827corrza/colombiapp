package com.josedev.colombiapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.josedev.colombiapp.R
import com.josedev.colombiapp.domain.repository.TouristicADetailEvent
import com.josedev.colombiapp.domain.state.TouristAttractionState
import com.josedev.colombiapp.presentation.TouristicADetailVM

@Composable
fun TouristicADetailScreen(
    navigation: NavHostController,
    id: String,
    modifier: Modifier = Modifier,
    vm: TouristicADetailVM = hiltViewModel()
) {

    val state by vm.state.collectAsState()
    val status by vm.status.collectAsState()

    LaunchedEffect(key1 = id) {
        vm.onEvent(TouristicADetailEvent.TouristicADetail(id.toLong()))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
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
            Log.d("Detail", state.toString())
            AttractionDetail(state)
        }
    }
}

@Composable
fun AttractionDetail(
    state: TouristAttractionState,
    modifier: Modifier = Modifier
) {
    if(state.images.isNotEmpty()){
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 20.dp),
            model = state.images[0],
            contentDescription = " Touristic Attraction Image: ${state.name}"
        )
    } else{
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = "Default image",
            modifier = modifier
                .clip(CircleShape)
                .size(60.dp)
                .background(Color.LightGray)
        )
    }
    Text(
        fontSize = 28.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        text = state.name,
        modifier = modifier.fillMaxWidth()
    )
    HorizontalDivider()
    Text(
        text = state.description,
        textAlign = TextAlign.Justify,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp, 20.dp)
    )
    HorizontalDivider()
    Row(
        modifier = modifier.padding(10.dp, 12.dp)
    ) {
        Text(
            text = "Coordinates: ",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${state.latitude}   ${state.longitude}"
        )
    }
    Row(
        modifier = modifier.padding(10.dp, 12.dp)
    ) {
        Text(
            text = "City: ",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = state.city?.name ?: ""
        )
    }
}