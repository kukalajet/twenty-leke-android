package com.jeton.twentyleke.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val invoiceCheckRepository: InvoiceCheckRepository
) : ViewModel() {
    private val _allInvoicesResult =
        MutableStateFlow<AllInvoicesResult>(AllInvoicesResult.Initial)
    val allInvoicesResult: StateFlow<AllInvoicesResult>
        get() = _allInvoicesResult

    init {
        getAllInvoices()
    }

    private fun getAllInvoices() {
        viewModelScope.launch { _allInvoicesResult.emit(AllInvoicesResult.Initial) }

        val invoices = invoiceCheckRepository.getAllInvoicesFromDB()
        invoices.observeForever {
            it?.let {
                viewModelScope.launch { _allInvoicesResult.emit(AllInvoicesResult.Success(it)) }
            } ?: run {
                viewModelScope.launch {
                    _allInvoicesResult.emit(
                        AllInvoicesResult.Failure("Couldn't retrieve stored invoices.")
                    )
                }
            }
        }
    }
}

sealed class AllInvoicesResult {
    object Initial : AllInvoicesResult()
    data class Success(val data: List<Invoice>) : AllInvoicesResult()
    data class Failure(val error: String) : AllInvoicesResult()
    object Loading : AllInvoicesResult()
}