package com.jeton.twentyleke.feature.detail.view

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jeton.twentyleke.core.network.model.Invoice

@Composable
fun DetailScreen(invoice: Invoice?) {
    if (invoice == null) return Box {}

    Text(invoice.id.toString())
}