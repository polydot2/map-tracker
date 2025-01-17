package com.poly.herewego.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.poly.herewego.ui.category.CategoryScreen
import com.poly.herewego.ui.discover.DiscoverScreen
import com.poly.herewego.ui.login.LoginScreen
import com.poly.herewego.ui.map.MapScreen
import com.poly.herewego.ui.place.PlaceScreen
import com.poly.herewego.ui.profile.ProfileScreen
import com.poly.herewego.ui.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data class LoginRoute(val id: String)

@Serializable
data class MapRoute(val id: String)

@Serializable
data class DiscoverRoute(val id: String)

@Serializable
data class SettingsRoute(val id: String)

@Serializable
data class ProfileRoute(val id: String)

@Serializable
data class PlacesRoute(val id: String)

@Serializable
data class CategoryRoute(val id: String)

@Composable
fun NavHostRouter(navController: NavHostController, innerPadding: PaddingValues) {
//    val colorStops = arrayOf(
//        0.8f to Color.Transparent,
//        1.0f to Color.Yellow,
//    )

    NavHost(
        navController, startDestination = LoginRoute(id = "ok"), modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
//            .background(
//                brush = Brush
//                    .verticalGradient(
//                        colorStops = colorStops,
//                    )
//            )
//            .padding(12.dp)
    ) {
        composable<LoginRoute> {
            LoginScreen("a") { navController.navigate(MapRoute(id = "ok")) }
        }
        composable<MapRoute> {
            MapScreen()
        }
        composable<DiscoverRoute> {
            DiscoverScreen()
        }
        composable<SettingsRoute> {
            SettingsScreen()
        }
        composable<ProfileRoute> {
            ProfileScreen("profile", { category -> navController.navigate(CategoryRoute(category)) }, { navController.navigate(DiscoverRoute("numb")) })
        }
        composable<PlacesRoute> {
            PlaceScreen("id")
        }
        composable<CategoryRoute> {
            CategoryScreen("{{params}}") { place -> navController.navigate(PlacesRoute(place)) }
        }
    }
}