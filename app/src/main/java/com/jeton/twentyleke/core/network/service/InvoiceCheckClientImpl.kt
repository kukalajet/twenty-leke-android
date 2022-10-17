package com.jeton.twentyleke.core.network.service

import com.jeton.twentyleke.core.network.model.FetchInvoiceRequest

class InvoiceCheckClientImpl(
    private val invoiceCheckService: InvoiceCheckService
) : InvoiceCheckClient {

    override suspend fun fetchInvoice(request: FetchInvoiceRequest) =
        invoiceCheckService.fetchInvoice(request)
}