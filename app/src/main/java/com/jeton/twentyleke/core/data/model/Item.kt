package com.jeton.twentyleke.core.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class Item(
    @Json(name = "id") @PrimaryKey val id: Long?,
    @Json(name = "name") @ColumnInfo(name = "name") val name: String?,
    @Json(name = "code") @ColumnInfo(name = "code") val code: String?,
    @Json(name = "unit") @ColumnInfo(name = "unit") val unit: String?,
    @Json(name = "quantity") @ColumnInfo(name = "quantity") val quantity: Double?,
    @Json(name = "unitPriceBeforeVat") @ColumnInfo(name = "unit_price_before_vat") val unitPriceBeforeVat: Double?,
    @Json(name = "unitPriceAfterVat") @ColumnInfo(name = "unit_price_after_vat") val unitPriceAfterVat: Double?,
    @Json(name = "priceBeforeVat") @ColumnInfo(name = "price_before_vat") val priceBeforeVat: Double?,
    @Json(name = "vatRate") @ColumnInfo(name = "vat_rate") val vatRate: Double?,
    @Json(name = "vatAmount") @ColumnInfo(name = "vat_amount") val vatAmount: Double?,
    @Json(name = "priceAfterVat") @ColumnInfo(name = "price_after_vat") val priceAfterVat: Double?,
)
