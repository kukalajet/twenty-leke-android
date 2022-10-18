package com.jeton.twentyleke

import android.app.Application
import com.jeton.twentyleke.core.di.appModule
import com.jeton.twentyleke.core.di.repositoryModule
import com.jeton.twentyleke.core.di.viewModelModule
import org.koin.core.context.startKoin

class TwentyLekeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}