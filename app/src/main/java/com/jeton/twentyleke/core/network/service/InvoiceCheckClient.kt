package com.jeton.twentyleke.core.network.service

import com.jeton.twentyleke.core.network.model.FetchInvoiceRequest
import com.jeton.twentyleke.core.network.model.Invoice
import retrofit2.Response

interface InvoiceCheckClient {

    suspend fun fetchInvoice(request: FetchInvoiceRequest): Response<Invoice>
}