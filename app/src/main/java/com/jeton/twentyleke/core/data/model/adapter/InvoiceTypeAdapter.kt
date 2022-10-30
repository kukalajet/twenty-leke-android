package com.jeton.twentyleke.core.data.model.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

enum class InvoiceType(val value: String) {
    CASH("CASH"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun fromValue(value: String?) =
            values().associateBy(InvoiceType::value)[value] ?: UNKNOWN
    }
}

class InvoiceTypeAdapter {
    @ToJson
    fun toJson(invoiceType: InvoiceType): String = invoiceType.value

    @FromJson
    fun fromJson(value: String?): InvoiceType = InvoiceType.fromValue(value ?: "UNKNOWN")
}