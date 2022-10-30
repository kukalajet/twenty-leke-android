package com.jeton.twentyleke.core.data.network.service

import com.jeton.twentyleke.core.data.model.Invoice
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InvoiceCheckService {

    @FormUrlEncoded
    @POST("verifyInvoice")
    suspend fun fetchInvoice(
        @Field("iic") iic: String,
        @Field("dateTimeCreated") dateTimeCreated: String,
        @Field("tin") tin: String
    ): Response<Invoice>
}