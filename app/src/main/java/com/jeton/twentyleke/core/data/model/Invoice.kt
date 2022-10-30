package com.jeton.twentyleke.core.data.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson

data class Invoice(
    @Json(name = "id") val id: Long?,
    @Json(name = "iic") val iic: String?,
    @Json(name = "totalPrice") val totalPrice: Double?,
    @Json(name = "invoiceOrderNumber") val invoiceOrderNumber: Double?,
    @Json(name = "businessUnit") val businessUnit: String?,
    @Json(name = "cashRegister") val cashRegister: String?,
    @Json(name = "issuerTaxNumber") val issuerTaxNumber: String?,
    @Json(name = "dateTimeCreated") val dateTimeCreated: String?,
    @Json(name = "fic") val fic: String?,
    @Json(name = "paymentMethod") val paymentMethod: List<PaymentMethod>?,
    @Json(name = "seller") val seller: Seller?,
    @Json(name = "items") val items: List<Item>?,
    @Json(name = "invoiceType") val invoiceType: InvoiceType?,
    @Json(name = "invoiceNumber") val invoiceNumber: String?,
    @Json(name = "totalPriceWithoutVAT") val totalPriceWithoutVAT: Double?,
    @Json(name = "totalVATAmount") val totalVATAmount: Double?,
    @Json(name = "operatorCode") val operatorCode: String?,
    @Json(name = "softwareCode") val softwareCode: String?,
    @Json(name = "iicSignature") val iicSignature: String?,
)

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