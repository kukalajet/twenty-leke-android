package com.jeton.twentyleke.core.data.model.dto

import com.jeton.twentyleke.core.data.model.adapter.TypeCode
import com.jeton.twentyleke.core.data.model.entity.PaymentMethodEntity
import com.squareup.moshi.Json

data class PaymentMethodDTO(
    @Json(name = "by") val by: Long?,
    @Json(name = "type") val type: String?,
    @Json(name = "amount") val amount: Double?,
    @Json(name = "typeCode") val typeCode: TypeCode?
) {
    fun toEntity(): PaymentMethodEntity {
        return PaymentMethodEntity(
            by = by,
            type = type,
            amount = amount,
            typeCode = typeCode
        )
    }
}

