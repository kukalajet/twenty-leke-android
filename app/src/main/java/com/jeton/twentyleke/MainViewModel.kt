package com.jeton.twentyleke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * For testing purposes. @Jeton
 */
class MainViewModel(private val invoiceCheckRepository: InvoiceCheckRepository) : ViewModel() {
    private val _invoice = MutableStateFlow<Invoice?>(null)
    val invoice: StateFlow<Invoice?>
        get() = _invoice

    init {
        setup()
    }

    private fun setup() {
        getInvoice()
    }

    fun getInvoice() {
        val iic = "E469949D81F26446F0AC5C8A75CAB288"
        val dateTimeCreated = "2022-06-05T10:24:24 02:00"
        val tin = "M12409049N"

        viewModelScope.launch {
            invoiceCheckRepository.getInvoice(iic, dateTimeCreated, tin).let { response ->
                val dto = response.body()
                val invoice = dto?.toInvoice()
                _invoice.emit(invoice)
            }
        }
    }
}