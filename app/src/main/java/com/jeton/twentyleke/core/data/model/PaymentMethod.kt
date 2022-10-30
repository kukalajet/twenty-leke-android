package com.jeton.twentyleke.core.data.model

import com.jeton.twentyleke.core.data.model.adapter.TypeCode
import com.squareup.moshi.Json

data class PaymentMethod(
    @Json(name = "by") val id: Long?,
    @Json(name = "type") val type: String?,
    @Json(name = "amount") val amount: Int?,
    @Json(name = "typeCode") val typeCode: TypeCode?
)