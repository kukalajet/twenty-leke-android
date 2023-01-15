package com.jeton.twentyleke.feature.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val invoiceCheckRepository: InvoiceCheckRepository
) : ViewModel() {
    private val _invoiceResult = MutableStateFlow<InvoiceFetchResult>(InvoiceFetchResult.Initial)
    val invoiceResult: StateFlow<InvoiceFetchResult>
        get() = _invoiceResult

    private val _invoiceSavingResult =
        MutableStateFlow<InvoiceSavingResult>(InvoiceSavingResult.Initial)
    val invoiceSavingResult: StateFlow<InvoiceSavingResult>
        get() = _invoiceSavingResult

    private val _invoiceRemovalResult =
        MutableStateFlow<InvoiceRemovalResult>(InvoiceRemovalResult.Initial)
    val invoiceRemovalResult: StateFlow<InvoiceRemovalResult>
        get() = _invoiceRemovalResult

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
            _invoiceSavingResult.emit(InvoiceSavingResult.Initial)
            try {
                invoiceCheckRepository.saveInvoice(invoice)
                _invoiceSavingResult.emit(InvoiceSavingResult.Success)
            } catch (error: Error) {
                _invoiceSavingResult.emit(InvoiceSavingResult.Failure("Couldn't save requested invoice."))
            }
        }
    }

    fun deleteInvoice(invoice: Invoice) {
        viewModelScope.launch {
            _invoiceRemovalResult.emit(InvoiceRemovalResult.Initial)
            try {
                invoiceCheckRepository.deleteInvoice(invoice)
                _invoiceRemovalResult.emit(InvoiceRemovalResult.Success)
            } catch (error: Error) {
                _invoiceRemovalResult.emit(InvoiceRemovalResult.Failure("Couldn't delete requested invoice."))
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

sealed class InvoiceSavingResult {
    object Initial : InvoiceSavingResult()
    object Success : InvoiceSavingResult()
    data class Failure(val error: String) : InvoiceSavingResult()
}

sealed class InvoiceRemovalResult {
    object Initial : InvoiceRemovalResult()
    object Success : InvoiceRemovalResult()
    data class Failure(val error: String) : InvoiceRemovalResult()
}