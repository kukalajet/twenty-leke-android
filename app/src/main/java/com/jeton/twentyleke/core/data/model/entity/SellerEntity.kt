package com.jeton.twentyleke.core.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeton.twentyleke.core.data.model.adapter.IdType
import com.squareup.moshi.Json

@Entity(tableName = "Seller")
data class SellerEntity(
    @PrimaryKey(autoGenerate = true) var sellerId: Int = 0,
    @Json(name = "idType") val idType: IdType,
    @Json(name = "idNum") val idNum: String,
    @Json(name = "name") val name: String,
    @Json(name = "address") val address: String,
    @Json(name = "town") val town: String,
    @Json(name = "country") val country: String
) {
    companion object {
        fun getMockedSample(): SellerEntity {
            return SellerEntity(
                idType = IdType.NUIS,
                idNum = "K91312511U",
                name = "EcoMarket Food",
                address = "Rruga Fortuzi",
                town = "Tirane",
                country = "ALB",
            )
        }
    }
}