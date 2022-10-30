package com.jeton.twentyleke.core.data.model

import com.jeton.twentyleke.core.data.model.adapter.IdType
import com.squareup.moshi.Json

data class Seller(
    @Json(name = "idType") val idType: IdType?,
    @Json(name = "idNum") val idNum: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "address") val address: String?,
    @Json(name = "town") val town: String?,
    @Json(name = "country") val country: String?
)