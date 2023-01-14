package com.jeton.twentyleke.feature.home.view

import androidx.compose.runtime.Composable

@Composable
fun HomeRoute(
    navigateToScan: () -> Unit,
    navigateToPersistedInvoiceDetail: (invoiceId: Long) -> Unit,
) {
    HomeScreen(navigateToScan, navigateToPersistedInvoiceDetail)
}