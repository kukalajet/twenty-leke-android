package com.jeton.twentyleke.core.data.model.dto

import com.jeton.twentyleke.core.data.model.entity.ItemEntity
import com.squareup.moshi.Json

data class ItemDTO(
    @Json(name = "id") val id: Long?,
    @Json(name = "name") val name: String?,
    @Json(name = "code") val code: String?,
    @Json(name = "unit") val unit: String?,
    @Json(name = "quantity") val quantity: Double?,
    @Json(name = "unitPriceBeforeVat") val unitPriceBeforeVat: Double?,
    @Json(name = "unitPriceAfterVat") val unitPriceAfterVat: Double?,
    @Json(name = "priceBeforeVat") val priceBeforeVat: Double?,
    @Json(name = "vatRate") val vatRate: Double?,
    @Json(name = "vatAmount") val vatAmount: Double?,
    @Json(name = "priceAfterVat") val priceAfterVat: Double?,
) {
    fun toEntity(invoiceId: Long): ItemEntity {
        return ItemEntity(
            id,
            invoiceId,
            name,
            code,
            unit,
            quantity,
            unitPriceBeforeVat,
            unitPriceAfterVat,
            priceBeforeVat,
            vatRate,
            vatAmount,
            priceAfterVat
        )
    }
}