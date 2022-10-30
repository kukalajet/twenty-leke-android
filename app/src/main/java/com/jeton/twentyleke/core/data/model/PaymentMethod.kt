package com.jeton.twentyleke.core.data.model

import androidx.room.ColumnInfo
import com.jeton.twentyleke.core.data.model.adapter.TypeCode
import com.squareup.moshi.Json

data class PaymentMethod(
    @Json(name = "by") @ColumnInfo(name = "by") val by: Long?,
    @Json(name = "type") @ColumnInfo(name = "type") val type: String?,
    @Json(name = "amount") @ColumnInfo(name = "amount") val amount: Int?,
    @Json(name = "typeCode") @ColumnInfo(name = "type_code") val typeCode: TypeCode?
)