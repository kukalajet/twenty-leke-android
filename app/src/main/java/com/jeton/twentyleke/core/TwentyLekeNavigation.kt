package com.jeton.twentyleke.core

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Destinations used in the [TwentyLekeApp].
 */
object TwentyLekeDestinations {
    const val HOME_ROUTE = "home"
    const val SCAN_ROUTE = "scan"
    const val DETAIL_ROUTE = "detail"
}

/**
 * Models the navigation actions in the app.
 *
 * Inspired by: https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/JetnewsNavigation.kt#L33
 */
class TwentyLekeNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(TwentyLekeDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToScan: () -> Unit = {
        navController.navigate(TwentyLekeDestinations.SCAN_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToDetail: () -> Unit = {
        navController.navigate(TwentyLekeDestinations.DETAIL_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}