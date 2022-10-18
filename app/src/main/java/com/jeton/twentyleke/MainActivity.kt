package com.jeton.twentyleke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.jeton.twentyleke.feature.TwentyLekeApp
import com.jeton.twentyleke.feature.home.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TwentyLekeApp {
                HomeScreen()
            }
        }
    }
}