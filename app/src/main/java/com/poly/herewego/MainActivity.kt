package com.poly.herewego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.poly.herewego.ui.discover.DiscoverScreen
import com.poly.herewego.ui.map.MapScreen
import com.poly.herewego.ui.theme.HerewegoTheme
import kotlinx.serialization.Serializable

@Serializable
data class Map(val id: String)

@Serializable
data class Places(val id: String)

@Serializable
data class Settings(val id: String)

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HerewegoTheme {
                val navController = rememberNavController()
                val topLevelRoutes = listOf(
                    TopLevelRoute("Map", Map(id = "A"), Icons.Rounded.Place),
                    TopLevelRoute("Discover", Places(id = "A"), Icons.Rounded.Favorite),
//                    TopLevelRoute("Profile", Settings(id = "A"), Icons.Rounded.AccountCircle),
                    TopLevelRoute("Settings", Settings(id = "A"), Icons.Sharp.Settings)
                )
                Scaffold(
                    modifier = Modifier.navigationBarsPadding(),
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            topLevelRoutes.forEach { topLevelRoute ->
                                this.BottomNavigationItem(
                                    icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) },
                                    label = { Text(topLevelRoute.name) },
                                    selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                                    onClick = {
                                        navController.navigate(topLevelRoute.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController, startDestination = Map(id = "ok"), Modifier.padding(innerPadding)) {
                        composable<Map> {
                            MapScreen()
                        }
                        composable<Places> {
                            DiscoverScreen()
                        }
                        composable<Settings> { backStackEntry ->
                            val profile = backStackEntry.toRoute<Settings>()
                            SettingsScreen(name = profile.id) { friendUserId ->
                                navController.navigate(route = Settings(id = friendUserId))
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun MapScreen(
//    name: String,
//    navigateToFriendProfile: (friendUserId: String) -> Unit
//) {
//    Column(modifier = Modifier.padding(12.dp)) {
//        Text(name)
//        Button(onClick = {}) { Text("return to another thing ?") }
//    }
//}

@Composable
fun SettingsScreen(name: String, navigateToFriendProfile: (friendUserId: String) -> Unit) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text("Settings", style = MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
        Button(onClick = {}) {
            Text("Noter l'application")
        }
        Button(onClick = {}) {
            Text("Suggestions / retour")
        }
        Button(onClick = {}) {
            Text("A propos")
        }
        Box(Modifier.height(12.dp))
        Button(onClick = {}) {
            Text("Se déconnecter")
        }
    }
}