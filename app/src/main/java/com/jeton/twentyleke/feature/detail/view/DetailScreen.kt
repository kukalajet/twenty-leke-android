package com.jeton.twentyleke.feature.detail.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeton.twentyleke.R
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.data.model.Seller
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(invoice: Invoice?, navigateBackToHome: () -> Unit) {
    if (invoice == null) return Box {}

    val topBarTitleValue = remember(invoice) { "Faturë ${invoice.invoiceOrderNumber?.toInt()}" }

//    val viewModel = getViewModel<DetailViewModel>()

    BackHandler(enabled = true, onBack = {
//        viewModel.reset()
        navigateBackToHome()
    })

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            colors = topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
            title = {
                Text(
                    text = topBarTitleValue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            navigationIcon = {
                IconButton(onClick = { navigateBackToHome() }) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer),
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(id = R.color.white))
        ) {
            HeaderSection(invoice = invoice)
        }
    }
}

@Composable
fun HeaderSection(invoice: Invoice) {
    val dateTimeCreated = remember(invoice) {
        try {
            return@remember invoice.getLocalDateTimeCreated()
        } catch (e: Exception) {
            return@remember null
        }
    }

    val totalPrice = invoice.totalPrice
    val totalPriceWithoutVAT = invoice.totalPriceWithoutVAT
    val totalVATAmount = invoice.totalVATAmount

    val invoiceOrderNumber = invoice.invoiceOrderNumber
    val year = dateTimeCreated?.year
    val cashRegister = invoice.cashRegister

    val seller = invoice.seller

    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomStart = 16.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        if (dateTimeCreated != null) TimeSection(dateTimeCreated)
        PriceSection(totalPrice, totalPriceWithoutVAT, totalVATAmount)
        if (seller != null) SellerSection(seller)
        InvoiceSignSection(invoiceOrderNumber, year, cashRegister)
    }
}

@Composable
fun TimeSection(dateTime: LocalDateTime) {
    val formattedDate = remember(dateTime) {
        val day = dateTime.dayOfMonth
        val month = dateTime.month.value
        val year = dateTime.year

        val hour = dateTime.hour
        val minute = dateTime.minute

        "$day/$month/$year $hour:$minute"
    }

    Text(
        text = formattedDate,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

@Composable
fun PriceSection(totalPrice: Double?, totalPriceWithoutVAT: Double?, totalVATAmount: Double?) {
    val formattedTotalPrice = remember(totalPrice) { "${totalPrice?.toInt()} Lekë" }
    val formattedTotalPriceWithoutVAT = remember(totalPriceWithoutVAT) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val formattedTotalPriceWithoutVAT = df.format(totalPriceWithoutVAT).toDouble()
        "Pa TVSH: $formattedTotalPriceWithoutVAT Lekë"
    }
    val formattedTotalVATAmount = remember(totalVATAmount) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val formattedTotalVATAmount = df.format(totalVATAmount).toDouble()
        "Shuma e TVSH-së: $formattedTotalVATAmount Lekë"
    }

    Column {
        if (totalPrice != null) Text(
            text = formattedTotalPrice,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        if (totalPriceWithoutVAT != null) Text(
            text = formattedTotalPriceWithoutVAT,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        if (totalVATAmount != null) Text(
            text = formattedTotalVATAmount,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun SellerSection(seller: Seller) {
    val name = remember(seller) { seller.name }
    val address = remember(seller) {
        val address = seller.address
        val town = seller.town
        val country = seller.country

        if (address == null && town == null && country == null) return@remember null

        var value = if (address != null) "$address" else null
        if (town != null) value = if (value != null) "$value, $town" else "$town"
        if (country != null) value = if (value != null) "$value, $country" else "$country"

        value
    }

    Column {
        if (name != null) Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        if (address != null) Text(
            text = address,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun InvoiceSignSection(invoiceOrderNumber: Double?, year: Int?, cashRegister: String?) {
    val sign = remember(invoiceOrderNumber, year, cashRegister) {
        if (invoiceOrderNumber == null && year == null && cashRegister == null) return@remember null

        var value = if (invoiceOrderNumber != null) "${invoiceOrderNumber.toInt()}" else null
        if (year != null) value = if (value != null) "$value, $year" else "$year"
        if (cashRegister != null) value =
            if (value != null) "$value, $cashRegister" else "$cashRegister"

        value
    } ?: return

    Row {
        Spacer(Modifier.weight(1f))
        Text(
            text = sign,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val invoice = Invoice.mock()
    DetailScreen(invoice = invoice, navigateBackToHome = { })
}