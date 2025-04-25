package com.josedev.colombiapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.josedev.colombiapp.navigation.AppNavigation
import com.josedev.colombiapp.navigation.routes.AppRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationComp(
    analytics: FirebaseAnalytics,
    modifier: Modifier = Modifier) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navigation = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { NavDrawerSheet(navigation, scope, drawerState) }
    )
    {
        Scaffold (
            topBar = { TopBar(drawerState, scope) }
        ){ innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                AppNavigation(analytics, navigation)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    drawerState: DrawerState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "ColombiApp", color = Color.White)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Main Menu", tint = Color.White)
            }
        }
    )
}

@Composable
fun NavDrawerSheet(
    navigation: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    ModalDrawerSheet {
        Text(text = "ColombiApp", modifier = Modifier.padding(18.dp), fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text(text = "Colombia") },
            selected = false,
            onClick = {
                scope.launch {
                    navigation.navigate(AppRoute.Colombia().route)
                    drawerState.close()
                }
            })
        NavigationDrawerItem(
            label = { Text(text = "Regions") },
            selected = false,
            onClick = {
                scope.launch {
                    navigation.navigate(AppRoute.Regions().route)
                    drawerState.close()
                }
            }
        )
        NavigationDrawerItem(
            label = { Text(text = "Presidents") },
            selected = false,
            onClick = {
                scope.launch {
                    navigation.navigate(AppRoute.Presidents().route)
                    drawerState.close()
                }
            }
        )
        HorizontalDivider()
        Text(text = "Tourism", modifier = Modifier.padding(18.dp), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text(text = "Touristic Attractions") },
            selected = false,
            onClick = {
                scope.launch {
                    navigation.navigate(AppRoute.TouristicAttractions().route)
                    drawerState.close()
                }
            }
        )
        HorizontalDivider()
        Text(text = "About", modifier = Modifier.padding(18.dp), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text(text = "Special Thanks") },
            selected = false,
            onClick = {
                scope.launch {
                    navigation.navigate(AppRoute.Thanks().route)
                    drawerState.close()
                }
            }
        )
    }
}
