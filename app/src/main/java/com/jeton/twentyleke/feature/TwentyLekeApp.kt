package com.jeton.twentyleke.feature

import androidx.compose.runtime.Composable
import com.jeton.twentyleke.core.ui.theme.TwentyLekeTheme

@Composable
fun TwentyLekeApp(
    content: @Composable() () -> Unit
) {
    TwentyLekeTheme {
        content
    }
}