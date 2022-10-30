package com.jeton.twentyleke.core.data.model

import androidx.room.ColumnInfo
import com.jeton.twentyleke.core.data.model.adapter.IdType
import com.squareup.moshi.Json

data class Seller(
    @Json(name = "idType") @ColumnInfo(name = "id_type") val idType: IdType?,
    @Json(name = "idNum") @ColumnInfo(name = "id_num") val idNum: String?,
    @Json(name = "name") @ColumnInfo(name = "name") val name: String?,
    @Json(name = "address") @ColumnInfo(name = "address") val address: String?,
    @Json(name = "town") @ColumnInfo(name = "town") val town: String?,
    @Json(name = "country") @ColumnInfo(name = "country") val country: String?
)