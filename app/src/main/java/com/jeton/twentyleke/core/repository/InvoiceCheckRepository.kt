package com.jeton.twentyleke.core.repository

import android.content.SharedPreferences
import com.jeton.twentyleke.core.network.service.InvoiceCheckClient

class InvoiceCheckRepository(
    sharedPreferences: SharedPreferences, private val invoiceCheckClient: InvoiceCheckClient
) {

    suspend fun getInvoice(
        iic: String,
        dateTimeCreated: String,
        tin: String,
    ) = invoiceCheckClient.fetchInvoice(iic, dateTimeCreated, tin)
}