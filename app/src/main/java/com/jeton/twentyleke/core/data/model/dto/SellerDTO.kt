package com.jeton.twentyleke.core.data.model.dto

import com.jeton.twentyleke.core.data.model.adapter.IdType
import com.jeton.twentyleke.core.data.model.entity.SellerEntity
import com.squareup.moshi.Json

data class SellerDTO(
    @Json(name = "idType") val idType: IdType,
    @Json(name = "idNum") val idNum: String,
    @Json(name = "name") val name: String,
    @Json(name = "address") val address: String,
    @Json(name = "town") val town: String,
    @Json(name = "country") val country: String
) {
    fun toEntity(): SellerEntity {
        return SellerEntity(
            idType = idType,
            idNum = idNum,
            name = name,
            address = address,
            town = town,
            country = country
        )
    }
}
