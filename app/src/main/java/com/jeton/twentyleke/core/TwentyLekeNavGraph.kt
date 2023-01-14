package com.jeton.twentyleke.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jeton.twentyleke.feature.detail.view.DetailRoute
import com.jeton.twentyleke.feature.home.view.HomeRoute
import com.jeton.twentyleke.feature.scan.view.ScanRoute

@Composable
fun TwentyLekeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TwentyLekeDestinations.HOME_ROUTE,
    navigateToHome: () -> Unit,
    navigateToScan: () -> Unit,
    navigateToInvoiceDetail: (invoiceId: Long?) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(TwentyLekeDestinations.HOME_ROUTE) {
            HomeRoute(navigateToScan, navigateToInvoiceDetail)
        }

        composable(TwentyLekeDestinations.SCAN_ROUTE) {
            ScanRoute(navigateToInvoiceDetail = navigateToInvoiceDetail)
        }

        composable(TwentyLekeDestinations.DETAIL_ROUTE) {
            // Do you think I don't hate this implementation too?
            // Take notes: https://stackoverflow.com/a/69060224
            DetailRoute(invoiceId = null, navigateBackToHome = navigateToHome)
        }

        composable(
            "${TwentyLekeDestinations.DETAIL_ROUTE}/{invoiceId}",
            arguments = listOf(navArgument("invoiceId") { type = NavType.LongType })
        ) { backStackEntry ->
            // Do you think I don't hate this implementation too?
            // Take notes: https://stackoverflow.com/a/69060224
            val invoiceId = backStackEntry.arguments?.getLong("invoiceId")
            DetailRoute(invoiceId = invoiceId, navigateBackToHome = navigateToHome)
        }
    }
}