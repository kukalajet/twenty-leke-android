package com.jeton.twentyleke.feature.detail.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jeton.twentyleke.feature.detail.viewmodel.DetailViewModel
import com.jeton.twentyleke.feature.detail.viewmodel.InvoiceFetchResult
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailRoute(
    invoiceId: Long?,
    navigateBackToHome: () -> Unit,
) {
    val alreadyStoredInvoice = remember(invoiceId) { invoiceId != null }
    val viewModel = getViewModel<DetailViewModel>()
    val invoiceResult = viewModel.invoiceResult.collectAsState()

    DisposableEffect(invoiceId) {
        viewModel.getInvoice(invoiceId)
        onDispose { viewModel.reset() }
    }

    when (invoiceResult.value) {
        is InvoiceFetchResult.Initial -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onSurface)
            }
        }
        is InvoiceFetchResult.Failure -> {
            Text("FAILED")
        }
        is InvoiceFetchResult.Success -> {
            val invoice = (invoiceResult.value as InvoiceFetchResult.Success).data
            DetailScreen(invoice, alreadyStoredInvoice, navigateBackToHome)
        }
    }
}