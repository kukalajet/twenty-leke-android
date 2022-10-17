package com.jeton.twentyleke.core.di

import com.jeton.twentyleke.MainViewModel
import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { return@viewModel MainViewModel(get<InvoiceCheckRepository>()) }
}