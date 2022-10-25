package com.jeton.twentyleke.core.di

import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import com.jeton.twentyleke.feature.detail.viewmodel.DetailViewModel
import com.jeton.twentyleke.feature.scan.viewmodel.ScanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { return@viewModel ScanViewModel(get<InvoiceCheckRepository>()) }
    viewModel { return@viewModel DetailViewModel(get<InvoiceCheckRepository>()) }
}