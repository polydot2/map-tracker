package com.poly.herewego.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
import androidx.navigation.compose.rememberNavController

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
    val colorStops = arrayOf(
        0.8f to Color.Transparent,
        1.0f to Color.Yellow,
    )

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
            ProfileScreen("profile") { category -> navController.navigate(CategoryRoute(category)) }
        }
        composable<PlacesRoute> {
            PlaceScreen("id")
        }
        composable<CategoryRoute> {
            CategoryScreen("{{params}}") { place -> navController.navigate(PlacesRoute(place)) }
        }
    }
}