package com.jeton.twentyleke.feature.scan.view

import androidx.compose.runtime.Composable

@Composable
fun ScanRoute(
    navigateToInvoiceDetail: (invoiceId: Long?) -> Unit
) {
    ScanScreen(navigateToInvoiceDetail = navigateToInvoiceDetail)
}