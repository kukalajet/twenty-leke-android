package com.jeton.twentyleke.feature.detail.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.data.model.entity.ItemEntity
import com.jeton.twentyleke.core.ui.theme.TwentyLekeTheme

@Composable
fun InvoiceItem(item: ItemEntity) {
    val name = remember(item) { item.name }
    val quantity = remember(item) { item.quantity }
    val priceAfterVat = remember(item) { item.priceAfterVat?.toInt() }

    Row(
        Modifier
            .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp))
            .fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            name?.let {
                Text(
                    text = name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                text = "x $quantity",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Text(
            text = "$priceAfterVat Lekë",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(PaddingValues(start = 8.dp))
        )
    }
}

@Preview
@Composable
fun PreviewInvoiceItem() {
    val invoice = Invoice.getMockedSample()
    val item = invoice.items?.first()
    TwentyLekeTheme {
        InvoiceItem(item = item!!)
    }
}