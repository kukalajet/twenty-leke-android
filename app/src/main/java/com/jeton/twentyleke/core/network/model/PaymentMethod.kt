package com.jeton.twentyleke.core.network.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson

data class PaymentMethod(
    @Json(name = "by") val id: Long,
    @Json(name = "type") val type: String,
    @Json(name = "amount") val amount: Int,
    @Json(name = "typeCode") val typeCode: TypeCode
)

enum class TypeCode(val value: String) {
    Banknote("BANKNOTE"),
    Unknown("UNKNOWN");

    companion object {
        fun fromValue(value: String) = values().associateBy(TypeCode::value)[value] ?: Unknown
    }
}

class TypeCodeAdapter {
    @ToJson
    fun toJson(typeCode: TypeCode): String = typeCode.value

    @FromJson
    fun fromJson(value: String): TypeCode = TypeCode.fromValue(value)
}