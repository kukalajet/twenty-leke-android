package com.jeton.twentyleke.feature.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.feature.home.viewmodel.AllInvoicesResult
import com.jeton.twentyleke.feature.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToScan: () -> Unit
) {
    val viewModel = getViewModel<HomeViewModel>()
    val allInvoicesResult = viewModel.allInvoicesResult.collectAsState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(horizontal = 40.dp),
                onClick = { navigateToScan() },
                shape = FloatingActionButtonDefaults.extendedFabShape,
                containerColor = MaterialTheme.colorScheme.primary,
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = "Add new invoice",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },
    ) { innerPadding ->
        when (allInvoicesResult.value) {
            is AllInvoicesResult.Failure -> {}
            is AllInvoicesResult.Loading -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onSurface)
                }
            }
            is AllInvoicesResult.Initial -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onSurface)
                }
            }
            is AllInvoicesResult.Success -> {
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
                                Color.Black
                            )
                            drawContent()
                            drawRect(
                                brush = Brush.verticalGradient(colors),
                                blendMode = BlendMode.DstOut
                            )
                        }
                ) {
                    items(items = (allInvoicesResult.value as AllInvoicesResult.Success).data) {
                        Invoice(it)
                    }
                    item { Spacer(modifier = Modifier.padding(40.dp)) }
                }
            }
        }
    }
}

@Composable
fun Invoice(invoice: Invoice) {
    val sellerName = remember(invoice) { invoice.seller?.name }
    val sellerAddress = remember(invoice) { invoice.seller?.address }
    val totalPrice = remember(invoice) {
        val totalPrice = invoice.header?.totalPrice?.toInt() ?: return@remember null
        "$totalPrice Lekë"
    }
    val time = remember(invoice) {

//        val dateTimeCreated = invoice.header?.getLocalDateTimeCreated() ?: return@remember null
//        "${dateTimeCreated.hour}:${dateTimeCreated.minute}"
        val dateTimeCreated = invoice.header?.dateTimeCreated ?: return@remember null
        "$dateTimeCreated"
    }


    Card() {
        Row(
            Modifier.padding(
                PaddingValues(
                    start = 16.dp,
                    end = 24.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(2f)) {
                sellerName?.let {
                    Text(
                        sellerName, maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                sellerAddress?.let {
                    Text(
                        sellerAddress, maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Column() {
                totalPrice?.let {
                    Text(
                        totalPrice, maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                time?.let {
                    Text(
                        time, maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
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

//@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navigateToScan = { })
}