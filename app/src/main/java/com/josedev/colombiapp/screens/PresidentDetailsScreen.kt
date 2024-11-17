package com.josedev.colombiapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.josedev.colombiapp.R
import com.josedev.colombiapp.domain.repository.PresidentDetailEvent
import com.josedev.colombiapp.domain.state.PresidentState
import com.josedev.colombiapp.presentation.PresidentDetailVM

@Composable
fun PresidentDetailsScreen(
    navigation: NavHostController,
    id: String,
    modifier: Modifier = Modifier,
    vm: PresidentDetailVM = hiltViewModel()
) {

    val state by vm.state.collectAsState()
    val status by vm.status.collectAsState()
    
    LaunchedEffect(key1 = id){
        vm.onEvent(PresidentDetailEvent.PresidentDetail(id.toLong()))
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if(status.isLoading){
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }else {
            PresidentDetail(state)
        }
    }
}

@Composable
fun PresidentDetail(
    state: PresidentState,
    modifier: Modifier = Modifier) 
{
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        if(state.image != "null"){
            AsyncImage(
                model = state.image,
                contentDescription = "Rafael Nunez",
                modifier = modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentScale = ContentScale.FillWidth
            )
        } else {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher),
                contentDescription = "xd",
                modifier = modifier
                    .clip(CircleShape)
                    .size(150.dp)
                    .background(Color.LightGray)
            )
        }
        Spacer(modifier.padding(12.dp))
        Column {
            Text(
                text = "${state.name} ${state.lastName}",
                textAlign = TextAlign.Left,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${state.startPeriodDate}     ${state.endPeriodDate ?: ""}",
                textAlign = TextAlign.Left
            )
        }
    }
    HorizontalDivider(modifier = modifier.padding(vertical = 20.dp))
    Text(
        text = state.description,
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Justify
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(vertical = 10.dp)
    ) {
        Text(
            text = "Political Party: ",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.padding(horizontal = 10.dp))
        Text(text = state.politicalParty)
    }
}