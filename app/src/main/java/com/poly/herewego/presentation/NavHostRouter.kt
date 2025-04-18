package com.poly.herewego.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.poly.herewego.presentation.category.CategoryScreen
import com.poly.herewego.presentation.discover.DiscoverScreen
import com.poly.herewego.presentation.home.HomeScreen
import com.poly.herewego.presentation.home.HomeViewModel
import com.poly.herewego.presentation.login.LoginScreen
import com.poly.herewego.presentation.map.MapScreen
import com.poly.herewego.presentation.passport.PassportScreen
import com.poly.herewego.presentation.place.PlaceScreen
import com.poly.herewego.presentation.profile.ProfileScreen
import com.poly.herewego.presentation.settings.SettingsScreen
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

@Serializable
data class HomeRoute(val id: String)

@Serializable
data class PassportRoute(val passportId: String)

@Composable
fun NavHostRouter(navController: NavHostController, innerPadding: PaddingValues) {
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
            LoginScreen("a") {
                navController.navigate(HomeRoute(id = "ok")) {
                    // Retirer LoginRoute de la pile pour éviter d'y retourner
                    popUpTo<LoginRoute> { inclusive = true }
                    // S'assurer que HomeRoute n'est pas recréé si déjà dans la pile
                    launchSingleTop = true
                }
            }
        }
        composable<HomeRoute> { backStackEntry ->
            HomeScreen(
                { navController.navigate(ProfileRoute("dumb")) },
                { passportId: String -> navController.navigate(PassportRoute(passportId)) },
                { navController.navigate(SettingsRoute("dumb")) },
                hiltViewModel(backStackEntry)
            )
        }
        composable<PassportRoute> { backStackEntry ->
            PassportScreen(
                backStackEntry.toRoute<PassportRoute>().passportId,
                {},
                hiltViewModel(backStackEntry)
            )
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
            PlaceScreen("id", false, { navController.popBackStack() })
        }
        composable<CategoryRoute> {
            CategoryScreen("{{params}}", { place ->
                navController.navigate(
                    PlacesRoute(place)
                )
            }, { navController.popBackStack() })
        }
    }
}