package com.josedev.colombiapp.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.josedev.colombiapp.components.TrackScreen
import com.josedev.colombiapp.utils.Utils

@Composable
fun ThanksScreen(
    analytics: FirebaseAnalytics,
    modifier: Modifier = Modifier) {
    val context = LocalContext.current

    TrackScreen(name = "thanks-screen", analytics = analytics)

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Thanks",
            modifier = modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 30.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "This application consumes the API Colombia endpoints. Special thanks for all those who contributed to this project.",
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Button(onClick = { Utils.linkToWebPage(context, "https://github.com/Mteheran/api-colombia") }, colors = ButtonColors(Color.White, Color.Black, Color.White, Color.White)) {
            Text(
                text = "Go to the API Colombia repository!",
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
    
}
