package com.jeton.twentyleke.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeton.twentyleke.feature.detail.view.DetailRoute
import com.jeton.twentyleke.feature.home.view.HomeRoute
import com.jeton.twentyleke.feature.scan.view.ScanRoute

@Composable
fun TwentyLekeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TwentyLekeDestinations.HOME_ROUTE,
    navigateToScan: () -> Unit,
    navigateToDetail: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(TwentyLekeDestinations.HOME_ROUTE) {
            HomeRoute(navigateToScan = navigateToScan)
        }

        composable(TwentyLekeDestinations.SCAN_ROUTE) {
            ScanRoute(navigateToDetail = navigateToDetail)
        }

        composable(TwentyLekeDestinations.DETAIL_ROUTE) {
            DetailRoute()
        }
    }
}