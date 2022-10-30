package com.jeton.twentyleke.core.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.jeton.twentyleke.core.data.model.adapter.InvoiceType
import com.squareup.moshi.Json

data class Invoice(
    @Json(name = "id") @PrimaryKey val id: Long?,
    @Json(name = "iic") @ColumnInfo(name = "iic") val iic: String?,
    @Json(name = "totalPrice") @ColumnInfo(name = "total_price") val totalPrice: Double?,
    @Json(name = "invoiceOrderNumber") @ColumnInfo(name = "invoice_order_number") val invoiceOrderNumber: Double?,
    @Json(name = "businessUnit") @ColumnInfo(name = "business_unit") val businessUnit: String?,
    @Json(name = "cashRegister") @ColumnInfo(name = "cash_register") val cashRegister: String?,
    @Json(name = "issuerTaxNumber") @ColumnInfo(name = "issuer_tax_number") val issuerTaxNumber: String?,
    @Json(name = "dateTimeCreated") @ColumnInfo(name = "date_time_created") val dateTimeCreated: String?,
    @Json(name = "fic") @ColumnInfo(name = "fic") val fic: String?,
    @Json(name = "paymentMethod") @ColumnInfo(name = "payment_method") val paymentMethod: List<PaymentMethod>?,
    @Json(name = "seller") @Embedded val seller: Seller?,
    @Json(name = "items") @Embedded val items: List<Item>?,
    @Json(name = "invoiceType") @Embedded val invoiceType: InvoiceType?,
    @Json(name = "invoiceNumber") @ColumnInfo(name = "invoice_number") val invoiceNumber: String?,
    @Json(name = "totalPriceWithoutVAT") @ColumnInfo(name = "total_price_without_vat") val totalPriceWithoutVAT: Double?,
    @Json(name = "totalVATAmount") @ColumnInfo(name = "total_vat_amount") val totalVATAmount: Double?,
    @Json(name = "operatorCode") @ColumnInfo(name = "operator_code") val operatorCode: String?,
    @Json(name = "softwareCode") @ColumnInfo(name = "software_code") val softwareCode: String?,
    @Json(name = "iicSignature") @ColumnInfo(name = "iic_signature") val iicSignature: String?,
)