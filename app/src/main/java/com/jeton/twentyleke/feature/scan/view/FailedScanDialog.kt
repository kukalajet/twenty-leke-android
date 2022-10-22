package com.jeton.twentyleke.feature.scan.view

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jeton.twentyleke.core.ui.theme.TwentyLekeTheme

@Composable
fun FailedScanDialog(
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                "Invalid QR code",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        },
        text = {
            Text(
                "The scanned QR code is not valid, make sure it is coming from a exempt invoice and scan it again.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        },
        icon = {
            Icon(
                Icons.Filled.Close,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onErrorContainer,
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirmButtonClick) {
                Text(
                    "OK",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.errorContainer
    )
}

@Preview
@Composable
fun PreviewFailedScanDialog() {
    TwentyLekeTheme {
        FailedScanDialog(onDismissRequest = { }, onConfirmButtonClick = { })
    }
}