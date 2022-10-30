package com.jeton.twentyleke.feature.scan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeton.twentyleke.core.network.model.Invoice
import com.jeton.twentyleke.core.repository.InvoiceCheckRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScanViewModel(
    private val invoiceCheckRepository: InvoiceCheckRepository
) : ViewModel() {

    private val _scanResult = MutableStateFlow<ScanResult>(ScanResult.Initial)
    val scanResult: StateFlow<ScanResult>
        get() = _scanResult

    fun onScannedValue(url: String) {
        if (_scanResult.value !is ScanResult.Initial) return

        val queryParameters = getQueryParameters(url)

        val isValid = queryParameters.isValid()
        if (!isValid) {
            viewModelScope.launch {
                _scanResult.emit(ScanResult.Failure("invalid scanned value"))
            }
            return
        }

        viewModelScope.launch {
            _scanResult.emit(ScanResult.Loading)
            invoiceCheckRepository.getInvoice(
                queryParameters.iic!!,
                queryParameters.dateTimeCreated!!,
                queryParameters.tin!!
            ).let { response ->
                if (response.isSuccessful) {
                    val invoice = response.body()
                    _scanResult.emit(ScanResult.Success(invoice!!))
                } else {
                    val errorBody = response.errorBody().toString()
                    _scanResult.emit(ScanResult.Failure(errorBody))
                }
            }
        }
    }

    fun reset() {
        viewModelScope.launch {
            _scanResult.emit(ScanResult.Initial)
        }
    }

    private fun getQueryParameters(url: String): Parameters {
        val base = "https://efiskalizimi-app.tatime.gov.al/invoice-check/#/verify?"
        val queries = url.replace(base, "")

        val splittedQueries = queries.split("&")
        val emptyParameters = Parameters(null, null, null)

        val parameters = splittedQueries.fold(
            emptyParameters,
        ) { parameters, query ->
            val divisorIndex = query.indexOf('=')
            if (divisorIndex == -1) {
                return@fold parameters
            }

            val key = query.substring(0, divisorIndex)
            val value = query.substring(divisorIndex + 1)

            when (key) {
                "iic" -> parameters.iic = value
                "crtd" -> {
                    val encodedSpaceRegex = "(%2B|%20)".toRegex()
                    parameters.dateTimeCreated = if (value.contains(encodedSpaceRegex)) {
                        value.replace(encodedSpaceRegex, "+")
                    } else {
                        value
                    }
                }
                "tin" -> parameters.tin = value
                else -> return@fold parameters
            }

            return@fold parameters
        }

        return parameters
    }
}

sealed class ScanResult {
    object Initial : ScanResult()
    data class Success(val data: Invoice) : ScanResult()
    data class Failure(val error: String) : ScanResult()
    object Loading : ScanResult()
}

private data class Parameters(
    var iic: String?, var dateTimeCreated: String?, var tin: String?
) {
    fun isValid(): Boolean =
        iic?.isNotEmpty() == true && dateTimeCreated?.isNotEmpty() == true && tin?.isNotEmpty() == true
}