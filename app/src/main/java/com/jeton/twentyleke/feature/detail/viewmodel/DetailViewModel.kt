package com.jeton.twentyleke.feature.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val invoiceCheckRepository: InvoiceCheckRepository
) : ViewModel() {
    private val _invoiceResult = MutableStateFlow<InvoiceFetchResult>(InvoiceFetchResult.Initial)
    val invoiceResult: StateFlow<InvoiceFetchResult>
        get() = _invoiceResult

    fun getInvoice(invoiceId: Long?) {
        viewModelScope.launch {
            _invoiceResult.emit(InvoiceFetchResult.Initial)
            val invoice =
                if (invoiceId != null) invoiceCheckRepository.getInvoiceByIdFromDB(invoiceId)
                else invoiceCheckRepository.getCachedInvoice()
            invoice?.let {
                _invoiceResult.emit(InvoiceFetchResult.Success(it))
            } ?: run {
                _invoiceResult.emit(InvoiceFetchResult.Failure("Couldn't retrieve the requested invoice"))
            }

        }
    }

    fun saveInvoice(invoice: Invoice) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                invoiceCheckRepository.saveInvoice(invoice)
            }
        }
    }

    fun reset() {
        invoiceCheckRepository.clearCachedInvoice()
    }
}

sealed class InvoiceFetchResult {
    object Initial : InvoiceFetchResult()
    data class Success(val data: Invoice) : InvoiceFetchResult()
    data class Failure(val error: String) : InvoiceFetchResult()
}