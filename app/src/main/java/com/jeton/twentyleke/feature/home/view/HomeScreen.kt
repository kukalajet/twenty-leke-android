package com.jeton.twentyleke.feature.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

@Composable
fun HomeScreen() {
    Scaffold(floatingActionButtonPosition = FabPosition.Center, floatingActionButton = {
        FloatingActionButton(onClick = { print("fab") }) {
            Icon(Icons.Filled.Add, contentDescription = null)
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("content")
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}