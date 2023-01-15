package com.jeton.twentyleke.core.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExtendableFloatingActionButton(
    modifier: Modifier = Modifier,
    extended: Boolean,
    text: @Composable () -> Unit,
    icon: @Composable () -> Unit,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        backgroundColor = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()

            AnimatedVisibility(visible = extended) {
                Row(modifier = Modifier.padding(horizontal = 4.dp)) {
                    text()
                }
            }
        }
    }
}

@Preview
@Composable
fun ExtendableFloatingActionButton() {
    ExtendableFloatingActionButton(
        extended = true,
        text = {
            Text(
                "Add Invoice",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        onClick = { },
        icon = {
            Icon(
                Icons.Rounded.Add,
                contentDescription = "Add an Invoice",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    )
}