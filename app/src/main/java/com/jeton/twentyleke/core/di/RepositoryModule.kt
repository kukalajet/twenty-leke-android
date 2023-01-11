package com.jeton.twentyleke.core.di

import android.content.SharedPreferences
import com.jeton.twentyleke.core.data.network.service.InvoiceCheckClient
import com.jeton.twentyleke.core.data.persistance.dao.InvoiceDao
import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        return@single InvoiceCheckRepository(
            get<SharedPreferences>(),
            get<InvoiceCheckClient>(),
            get<InvoiceDao>(),
        )
    }
}