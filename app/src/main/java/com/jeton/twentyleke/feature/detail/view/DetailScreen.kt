package com.jeton.twentyleke.feature.detail.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
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
import com.jeton.twentyleke.core.data.model.entity.SellerEntity
import com.jeton.twentyleke.core.ui.theme.TwentyLekeTheme
import com.jeton.twentyleke.feature.detail.viewmodel.DetailViewModel
import com.jeton.twentyleke.feature.detail.viewmodel.InvoiceRemovalResult
import com.jeton.twentyleke.feature.detail.viewmodel.InvoiceSavingResult
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    invoice: Invoice,
    alreadyStoredInvoice: Boolean,
    navigateBackToHome: () -> Unit
) {
    val viewModel = getViewModel<DetailViewModel>()
    val invoiceSavingResult = viewModel.invoiceSavingResult.collectAsState()
    val invoiceRemovalResult = viewModel.invoiceRemovalResult.collectAsState()

    val invoiceItems = remember(invoice) { invoice.items }
    val topBarTitleValue =
        remember(invoice) { "Faturë ${invoice.header?.invoiceOrderNumber?.toInt()}" }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    LaunchedEffect(invoiceRemovalResult.value) {
        when (invoiceRemovalResult.value) {
            is InvoiceRemovalResult.Initial -> {}
            is InvoiceRemovalResult.Success -> {
                navigateBackToHome()
            }
            is InvoiceRemovalResult.Failure -> {
                val error = (invoiceRemovalResult.value as InvoiceRemovalResult.Failure).error
                scope.launch { snackbarHostState.showSnackbar(error) }
            }
        }
    }

    LaunchedEffect(invoiceSavingResult.value) {
        when (invoiceSavingResult.value) {
            is InvoiceSavingResult.Initial -> {}
            is InvoiceSavingResult.Success -> {
                navigateBackToHome()
            }
            is InvoiceSavingResult.Failure -> {
                val error = (invoiceSavingResult.value as InvoiceSavingResult.Failure).error
                scope.launch { snackbarHostState.showSnackbar(error) }
            }
        }
    }

    BackHandler(
        enabled = true,
        onBack = { navigateBackToHome() }
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
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
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxHeight(1f)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
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
                itemsIndexed(items = invoiceItems!!) { index, item ->
                    InvoiceItem(item = item)
                    if (index < invoiceItems.lastIndex)
                        InvoiceItemDivider()
                }
                item { Spacer(modifier = Modifier.padding(40.dp)) }
            }

            ProcessInvoiceButton(
                alreadyStoredInvoice = alreadyStoredInvoice,
                modifier = Modifier.align(Alignment.BottomCenter),
                onAdd = { viewModel.saveInvoice(invoice) },
                onDelete = { viewModel.deleteInvoice(invoice) }
            )
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
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
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
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.padding(bottom = 2.dp)
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

    Column(Modifier.padding(vertical = 2.dp)) {
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

    Column(Modifier.padding(vertical = 2.dp)) {
        if (name != null) Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        if (address != null) Text(
            text = address,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
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

    Row(modifier = Modifier.padding(top = 8.dp)) {
        Spacer(Modifier.weight(1f))
        Text(
            text = sign,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun InvoiceItemDivider() {
    Divider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun ProcessInvoiceButton(
    alreadyStoredInvoice: Boolean,
    modifier: Modifier = Modifier,
    onAdd: () -> Unit,
    onDelete: () -> Unit
) {
    val title = remember(alreadyStoredInvoice) {
        if (alreadyStoredInvoice) return@remember "Delete Invoice"
        else return@remember "Add Invoice"
    }
    val icon = remember(alreadyStoredInvoice) {
        if (alreadyStoredInvoice) return@remember Icons.Filled.Delete
        else return@remember Icons.Filled.Done
    }

    Button(
        onClick = {
            if (alreadyStoredInvoice)
                onDelete()
            else
                onAdd()
        },
        modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    val invoice = Invoice.getMockedSample()
    TwentyLekeTheme {
        DetailScreen(
            invoice = invoice,
            alreadyStoredInvoice = true,
            navigateBackToHome = { }
        )
    }
}