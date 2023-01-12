package com.jeton.twentyleke.core.data.model.dto

import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.data.model.adapter.InvoiceType
import com.jeton.twentyleke.core.data.model.entity.HeaderEntity
import com.jeton.twentyleke.core.data.model.entity.ItemEntity
import com.jeton.twentyleke.core.data.model.entity.PaymentMethodEntity
import com.squareup.moshi.Json

data class InvoiceDTO(
    @Json(name = "id") val id: Long?,
    @Json(name = "iic") val iic: String?,
    @Json(name = "totalPrice") val totalPrice: Double?,
    @Json(name = "invoiceOrderNumber") val invoiceOrderNumber: Double?,
    @Json(name = "businessUnit") val businessUnit: String?,
    @Json(name = "cashRegister") val cashRegister: String?,
    @Json(name = "issuerTaxNumber") val issuerTaxNumber: String?,
    @Json(name = "dateTimeCreated") val dateTimeCreated: String?,
    @Json(name = "fic") val fic: String?,
    @Json(name = "paymentMethod") val paymentMethod: List<PaymentMethodDTO>?,
    @Json(name = "seller") val seller: SellerDTO?,
    @Json(name = "items") val items: List<ItemDTO>?,
    @Json(name = "invoiceType") val invoiceType: InvoiceType?,
    @Json(name = "invoiceNumber") val invoiceNumber: String?,
    @Json(name = "totalPriceWithoutVAT") val totalPriceWithoutVAT: Double?,
    @Json(name = "totalVATAmount") val totalVATAmount: Double?,
    @Json(name = "operatorCode") val operatorCode: String?,
    @Json(name = "softwareCode") val softwareCode: String?,
    @Json(name = "iicSignature") val iicSignature: String?,
) {
    fun toInvoice(): Invoice {
        val header = getHeaderEntity()
        val seller = seller?.toEntity()
        val items = getItemEntities()
        val paymentMethods = getPaymentMethodEntities()
        return Invoice(
            header = header,
            seller = seller,
            items = items,
            paymentMethods = paymentMethods
        )
    }

    private fun getHeaderEntity(): HeaderEntity {
        return HeaderEntity(
            id,
            iic,
            totalPrice,
            invoiceOrderNumber,
            businessUnit,
            cashRegister,
            issuerTaxNumber,
            dateTimeCreated,
            fic,
            invoiceType,
            invoiceNumber,
            totalPriceWithoutVAT,
            totalVATAmount,
            operatorCode,
            softwareCode,
            iicSignature,
        )
    }

    private fun getPaymentMethodEntities(): List<PaymentMethodEntity>? {
        return paymentMethod?.map { it.toEntity() }
    }

    private fun getItemEntities(): List<ItemEntity>? {
        return items?.map { it.toEntity() }
    }
}