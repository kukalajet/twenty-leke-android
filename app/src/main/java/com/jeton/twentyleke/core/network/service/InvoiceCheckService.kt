package com.jeton.twentyleke.core.network.service

import com.jeton.twentyleke.core.network.model.FetchInvoiceRequest
import com.jeton.twentyleke.core.network.model.Invoice
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface InvoiceCheckService {

    @GET("verifyInvoice")
    suspend fun fetchInvoice(@Body request: FetchInvoiceRequest): Response<Invoice>
}