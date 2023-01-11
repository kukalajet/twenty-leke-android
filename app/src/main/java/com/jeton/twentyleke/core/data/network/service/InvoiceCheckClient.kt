package com.jeton.twentyleke.core.data.network.service

import com.jeton.twentyleke.core.data.model.dto.InvoiceDTO
import retrofit2.Response

interface InvoiceCheckClient {

    suspend fun fetchInvoice(
        iic: String,
        dateTimeCreated: String,
        tin: String
    ): Response<InvoiceDTO>
}