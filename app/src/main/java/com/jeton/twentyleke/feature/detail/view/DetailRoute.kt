package com.jeton.twentyleke.feature.detail.view

import androidx.compose.runtime.Composable
import com.jeton.twentyleke.core.network.model.Invoice

@Composable
fun DetailRoute(
    invoice: Invoice?,
    navigateBackToHome: () -> Unit,
) {
    DetailScreen(
        invoice = invoice!!,
        navigateBackToHome = navigateBackToHome
    )
}