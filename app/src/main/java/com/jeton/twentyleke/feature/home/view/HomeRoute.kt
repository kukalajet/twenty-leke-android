package com.jeton.twentyleke.feature.home.view

import androidx.compose.runtime.Composable

@Composable
fun HomeRoute(
    navigateToScan: () -> Unit
) {
    HomeScreen(navigateToScan = navigateToScan)
}