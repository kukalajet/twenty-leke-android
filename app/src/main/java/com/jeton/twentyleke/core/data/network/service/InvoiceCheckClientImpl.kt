package com.jeton.twentyleke.core.data.network.service

class InvoiceCheckClientImpl(
    private val invoiceCheckService: InvoiceCheckService
) : InvoiceCheckClient {

    override suspend fun fetchInvoice(iic: String, dateTimeCreated: String, tin: String) =
        invoiceCheckService.fetchInvoice(iic, dateTimeCreated, tin)
}