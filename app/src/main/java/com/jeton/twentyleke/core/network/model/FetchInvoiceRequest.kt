package com.jeton.twentyleke.core.network.model

data class FetchInvoiceRequest(
    val iic: String,
    val dateTimeCreated: String,
    val tin: String,
)
