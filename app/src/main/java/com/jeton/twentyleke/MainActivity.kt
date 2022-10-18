package com.jeton.twentyleke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jeton.twentyleke.feature.TwentyLekeApp
import com.jeton.twentyleke.feature.home.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TwentyLekeApp {
                HomeScreen()
            }
        }
    }
}