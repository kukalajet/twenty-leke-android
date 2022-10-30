package com.jeton.twentyleke.feature.detail.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.feature.detail.viewmodel.DetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(invoice: Invoice?, navigateBackToHome: () -> Unit) {
    if (invoice == null) return Box {}

    val viewModel = getViewModel<DetailViewModel>()

    BackHandler(enabled = true, onBack = {
        viewModel.reset()
        navigateBackToHome()
    })

    Text(invoice.id.toString())
}