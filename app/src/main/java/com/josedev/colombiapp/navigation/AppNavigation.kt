package com.josedev.colombiapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josedev.colombiapp.navigation.routes.AppRoute
import com.josedev.colombiapp.screens.ColombiaScreen
import com.josedev.colombiapp.screens.PresidentDetailsScreen
import com.josedev.colombiapp.screens.PresidentsScreen
import com.josedev.colombiapp.screens.RegionDetailsScreen
import com.josedev.colombiapp.screens.RegionsScreen
import com.josedev.colombiapp.screens.StateDetailScreen
import com.josedev.colombiapp.screens.ThanksScreen
import com.josedev.colombiapp.screens.TouristicADetailScreen
import com.josedev.colombiapp.screens.TouristicAttractionsScreen
import javax.inject.Inject

@Composable
fun AppNavigation(
    navigation: NavHostController,
    modifier: Modifier = Modifier) {


    NavHost(navController = navigation, startDestination = AppRoute.Colombia().route){
        composable(AppRoute.Thanks().route){
            ThanksScreen()
        }
        composable(AppRoute.Colombia().route){
            ColombiaScreen()
        }
        composable(AppRoute.Regions().route){
            RegionsScreen(navigation)
        }
        composable(AppRoute.Presidents().route){
            PresidentsScreen(navigation)
        }
        composable(AppRoute.TouristicAttractions().route){
            TouristicAttractionsScreen(navigation)
        }
        composable(AppRoute.Regions().route){
            RegionsScreen(navigation)
        }
        composable(AppRoute.DepartmentsByRegionIdStatic().route){
            RegionDetailsScreen(navigation, it.arguments?.getString("id")!!)
        }
        composable(AppRoute.StateDetailByIdStatic().route){
            StateDetailScreen(navigation, it.arguments?.getString("id")!!)
        }
        composable(AppRoute.PresidentDetailByIdStatic().route){
            PresidentDetailsScreen(navigation, it.arguments?.getString("id")!!)
        }
        composable(AppRoute.TouristicAttractionsByIdStatic().route){
            TouristicADetailScreen(navigation, it.arguments?.getString("id")!!)
        }
    }

}