package com.jeton.twentyleke.core.data.model.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

enum class TypeCode(val value: String) {
    BANKNOTE("BANKNOTE"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun fromValue(value: String?) = values().associateBy(TypeCode::value)[value] ?: UNKNOWN
    }
}

class TypeCodeAdapter {
    @ToJson
    fun toJson(typeCode: TypeCode): String = typeCode.value

    @FromJson
    fun fromJson(value: String?): TypeCode = TypeCode.fromValue(value ?: "UNKNOWN")
}