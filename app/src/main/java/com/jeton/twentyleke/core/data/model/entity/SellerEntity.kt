package com.jeton.twentyleke.core.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeton.twentyleke.core.data.model.adapter.IdType

@Entity(tableName = "Seller")
data class SellerEntity(
    @PrimaryKey(autoGenerate = true) var sellerId: Int = 0,
    val invoiceId: Long,
    val idType: IdType?,
    val idNum: String?,
    val name: String?,
    val address: String?,
    val town: String?,
    val country: String?
) {
    companion object {
        fun getMockedSample(invoiceId: Long): SellerEntity {
            return SellerEntity(
                invoiceId = invoiceId,
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