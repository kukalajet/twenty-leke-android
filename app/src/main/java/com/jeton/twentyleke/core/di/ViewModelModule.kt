package com.jeton.twentyleke.core.di

import com.jeton.twentyleke.MainViewModel
import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import com.jeton.twentyleke.feature.scan.viewmodel.ScanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { return@viewModel MainViewModel(get<InvoiceCheckRepository>()) }
    viewModel { return@viewModel ScanViewModel(get<InvoiceCheckRepository>()) }
}