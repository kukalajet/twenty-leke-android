package com.jeton.twentyleke.feature.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Invoice(invoice: Invoice, onPress: () -> Unit = {}) {
    val sellerName = remember(invoice) { invoice.seller?.name }
    val sellerAddress = remember(invoice) { invoice.seller?.address }
    val totalPrice = remember(invoice) {
        val totalPrice = invoice.header?.totalPrice?.toInt() ?: return@remember null
        "$totalPrice Lekë"
    }
    val time = remember(invoice) {
        try {
            val dateTimeCreated = invoice.header?.getLocalDateTimeCreated()
            val hour = dateTimeCreated?.hour
            val minute = dateTimeCreated?.minute.toString().padStart(2, '0')

            return@remember "$hour:$minute"
        } catch (e: Exception) {
            return@remember null
        }
    }


    Card(
        onClick = onPress,
        modifier = Modifier.padding(PaddingValues(all = 8.dp)),
    ) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.weight(1f)
            ) {
                sellerName?.let {
                    Text(
                        sellerName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                sellerAddress?.let {
                    Text(
                        sellerAddress,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                time?.let {
                    Text(
                        time,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                totalPrice?.let {
                    Text(
                        totalPrice,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewInvoice() {
    val invoice = Invoice.getMockedSample()
    Invoice(invoice = invoice)
}