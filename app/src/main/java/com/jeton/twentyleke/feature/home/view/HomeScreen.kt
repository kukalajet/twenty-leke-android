package com.jeton.twentyleke.feature.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeton.twentyleke.core.ui.composable.ExtendableFloatingActionButton
import com.jeton.twentyleke.core.ui.composable.isScrollingUp
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

    val listState = rememberLazyListState()

    Scaffold(
        floatingActionButton = {
            ExtendableFloatingActionButton(
                extended = listState.isScrollingUp(),
                text = {
                    Text(
                        "Add Invoice",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                onClick = { navigateToScan() },
                icon = {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "Add an Invoice",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
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
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    items(items = (allInvoicesResult.value as AllInvoicesResult.Success).data) {
                        Invoice(it)
                    }
                    item { Spacer(modifier = Modifier.size(80.dp)) }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navigateToScan = { })
}