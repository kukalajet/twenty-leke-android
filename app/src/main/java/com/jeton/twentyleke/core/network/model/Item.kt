package com.jeton.twentyleke.core.network.model

import com.squareup.moshi.Json

data class Item(
    @Json(name = "id") val id: Long,
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
)
