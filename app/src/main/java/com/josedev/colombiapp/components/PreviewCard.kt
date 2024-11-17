package com.josedev.colombiapp.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.josedev.colombiapp.R

@Composable
fun PreviewCard(
    modifier: Modifier = Modifier,
    startPeriod: String? = "",
    endPeriod: String? = "",
    title: String = "",
    image: String? = null,
    goTo: () ->Unit
) {
    Card (
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                goTo()
            }
    ){
        Row(
            modifier =modifier.padding(10.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(image != "null"){
                AsyncImage(
                    model = image,
                    contentDescription = "Rafael Nunez",
                    contentScale = ContentScale.FillWidth,
                    modifier = modifier.size(60.dp).clip(CircleShape).background(Color.LightGray)
                )
            } else {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = "xd",
                    modifier = modifier.clip(CircleShape).size(60.dp).background(Color.LightGray)
                )
            }
            Column(
                modifier = modifier.padding(10.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$startPeriod     ${endPeriod ?: ""}", // TODO avoid to show null instead of  empty string
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)
@Composable
fun PreviewCardPreview() {
    PreviewCard(
        startPeriod = "1887-06-04",
        endPeriod = "1888-08-07",
        goTo = { testFun("test") }
    )
}

fun testFun(name: String){
    Log.d("Card", "PreviewCardPreview: $name")
}