package com.jeton.twentyleke.feature.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val invoiceCheckRepository: InvoiceCheckRepository
) : ViewModel() {

    fun saveInvoice(invoice: Invoice) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                invoiceCheckRepository.saveInvoiceInDB(invoice)
            }
        }
    }

    fun reset() {
        invoiceCheckRepository.clearCachedInvoice()
    }
}