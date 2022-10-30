package com.jeton.twentyleke.core.data.model.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

enum class IdType(val value: String) {
    NUIS("NUIS"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun fromValue(value: String?) = values().associateBy(IdType::value)[value] ?: UNKNOWN
    }
}

class IdTypeAdapter {
    @ToJson
    fun toJson(typeCode: TypeCode): String = typeCode.value

    @FromJson
    fun fromJson(value: String?): TypeCode = TypeCode.fromValue(value ?: "UNKNOWN")
}
