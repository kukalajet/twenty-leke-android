package com.jeton.twentyleke.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jeton.twentyleke.core.ui.theme.TwentyLekeTheme

@Composable
fun TwentyLekeApp() {
    TwentyLekeTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            TwentyLekeNavigationActions(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: TwentyLekeDestinations.HOME_ROUTE

        TwentyLekeNavGraph(
            navController = navController,
            navigateToScan = navigationActions.navigateToScan,
            navigateToDetail = navigationActions.navigateToDetail,
        )
    }
}