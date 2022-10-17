package com.jeton.twentyleke.core.repository

import com.jeton.twentyleke.core.network.service.InvoiceCheckClient

class InvoiceCheckRepository(
    private val invoiceCheckClient: InvoiceCheckClient
) {

    suspend fun getInvoice(
        iic: String,
        dateTimeCreated: String,
        tin: String,
    ) = invoiceCheckClient.fetchInvoice(iic, dateTimeCreated, tin)
}