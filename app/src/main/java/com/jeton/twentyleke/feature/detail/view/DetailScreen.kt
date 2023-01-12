package com.jeton.twentyleke.feature.detail.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.data.model.entity.ItemEntity
import com.jeton.twentyleke.core.data.model.entity.SellerEntity
import com.jeton.twentyleke.feature.detail.viewmodel.DetailViewModel
import org.koin.androidx.compose.getViewModel
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(invoice: Invoice?, navigateBackToHome: () -> Unit) {
    if (invoice == null) return Box {}

    val invoiceItems = remember(invoice) { invoice.items }
    val topBarTitleValue = remember(invoice) {
        "Faturë ${invoice.header?.invoiceOrderNumber?.toInt()}"
    }

    val viewModel = getViewModel<DetailViewModel>()

    BackHandler(enabled = true, onBack = {
        viewModel.reset()
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
    }, containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                // https://stackoverflow.com/a/68738725
                .graphicsLayer { alpha = 0.99F }
                .drawWithContent {
                    val colors = listOf(
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Black
                    )
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(colors),
                        blendMode = BlendMode.DstOut
                    )
                }
        ) {
            item {
                HeaderSection(invoice = invoice)
                Box(
                    Modifier
                        .fillMaxWidth(1f)
                        .height(4.dp)
                )
            }
            items(
                items = invoiceItems!!,
                itemContent = { InvoiceItem(item = it) }
            )
            item { Spacer(modifier = Modifier.padding(100.dp)) }
        }
    }
}

@Composable
fun HeaderSection(invoice: Invoice) {
    val dateTimeCreated = remember(invoice) {
        try {
            return@remember invoice.header?.getLocalDateTimeCreated()
        } catch (e: Exception) {
            return@remember null
        }
    }

    val totalPrice = invoice.header?.totalPrice
    val totalPriceWithoutVAT = invoice.header?.totalPriceWithoutVAT
    val totalVATAmount = invoice.header?.totalVATAmount

    val invoiceOrderNumber = invoice.header?.invoiceOrderNumber
    val year = dateTimeCreated?.year
    val cashRegister = invoice.header?.cashRegister

    val seller = invoice.seller

    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomStart = 24.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
    ) {
        dateTimeCreated?.let { TimeSection(it) }
        PriceSection(totalPrice, totalPriceWithoutVAT, totalVATAmount)
        seller?.let { SellerSection(seller) }
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
        if (totalPriceWithoutVAT == null) return@remember ""
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val formattedTotalPriceWithoutVAT = df.format(totalPriceWithoutVAT).toDouble()
        "Pa TVSH: $formattedTotalPriceWithoutVAT Lekë"
    }
    val formattedTotalVATAmount = remember(totalVATAmount) {
        if (totalVATAmount == null) return@remember ""
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
fun SellerSection(seller: SellerEntity) {
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

@Composable
fun InvoiceItem(item: ItemEntity) {
    val name = remember(item) { item.name }
    val quantity = remember(item) { item.quantity }
    val priceAfterVat = remember(item) { item.priceAfterVat }

    Box(Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row(
            Modifier
                .background(
                    MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(PaddingValues(all = 8.dp))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(2f)) {
                name?.let {
                    Text(
                        text = name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                Text(
                    text = "x $quantity",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Text(
                text = "$priceAfterVat Lekë",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(PaddingValues(start = 4.dp))
            )
        }
    }
}

@Preview
@Composable
fun PreviewInvoiceItem() {
    val invoice = Invoice.getMockedSample()
    val item = invoice.items?.first()
    InvoiceItem(item = item!!)
}

//@Preview
//@Composable
//fun PreviewDetailScreen() {
//    val invoice = Invoice.getMockedSample()
//    DetailScreen(invoice = invoice, navigateBackToHome = { })
//}