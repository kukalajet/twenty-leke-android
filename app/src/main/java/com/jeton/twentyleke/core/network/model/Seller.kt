package com.jeton.twentyleke.core.network.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson

data class Seller(
    @Json(name = "idType") val idType: IdType,
    @Json(name = "idNum") val idNum: String,
    @Json(name = "name") val name: String,
    @Json(name = "address") val address: String,
    @Json(name = "town") val town: String,
    @Json(name = "country") val country: String
)

enum class IdType(val value: String) {
    Nuis("NUID"),
    Unknown("UNKNOWN");

    companion object {
        fun fromValue(value: String) = values().associateBy(IdType::value)[value] ?: Unknown
    }
}

class IdTypeAdapter {
    @ToJson
    fun toJson(typeCode: TypeCode): String = typeCode.value

    @FromJson
    fun fromJson(value: String): TypeCode = TypeCode.fromValue(value)
}
