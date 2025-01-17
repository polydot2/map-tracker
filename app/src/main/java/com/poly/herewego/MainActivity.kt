package com.poly.herewego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.poly.herewego.ui.CategoryRoute
import com.poly.herewego.ui.DiscoverRoute
import com.poly.herewego.ui.MapRoute
import com.poly.herewego.ui.NavHostRouter
import com.poly.herewego.ui.ProfileRoute
import com.poly.herewego.ui.SettingsRoute

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                var shouldShowBottomBar by rememberSaveable {
                    mutableStateOf(false)
                }

                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }

                val topLevelRoutes = listOf(
                    TopLevelRoute("Map", MapRoute(id = "A"), Icons.Rounded.Place),
                    TopLevelRoute("Explore", DiscoverRoute(id = "A"), Icons.Rounded.Favorite),
                    TopLevelRoute("Profile", ProfileRoute(id = "A"), Icons.Rounded.AccountCircle),
                    TopLevelRoute("Settings", SettingsRoute(id = "A"), Icons.Rounded.Settings)
                )

                LaunchedEffect(key1 = navController) {
                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        shouldShowBottomBar =
                            destination.hasRoute(MapRoute::class)
                                    || destination.hasRoute(DiscoverRoute::class)
                                    || destination.hasRoute(ProfileRoute::class)
                                    || destination.hasRoute(SettingsRoute::class)

                        if (destination.hasRoute(MapRoute::class)) {
                            selectedItemIndex = 0
                        }
                    }
                }

                Scaffold(
                    Modifier.navigationBarsPadding(),
                    bottomBar = {
                        AnimatedVisibility(visible = shouldShowBottomBar, enter = fadeIn(), exit = fadeOut()) {
                            NavigationBar {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                topLevelRoutes.forEach { topLevelRoute ->
                                    BottomNavigationItem(
                                        icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) },
                                        label = {
                                            Text(
                                                text = topLevelRoute.name, softWrap = false,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.padding(horizontal = 2.dp)
                                            )
                                        },
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
                    }
                ) { innerPadding ->
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    // top padding change to fullscreen on certain screen
//                    NavHostRouter(navController, if (currentDestination?.hasRoute(CategoryRoute::class) == true) PaddingValues(top = 0.dp) else innerPadding)
                    NavHostRouter(navController, innerPadding)
                }
            }
        }
    }
}
