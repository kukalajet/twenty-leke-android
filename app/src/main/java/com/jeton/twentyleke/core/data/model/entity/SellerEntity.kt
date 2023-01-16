package com.jeton.twentyleke.core.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeton.twentyleke.core.data.model.adapter.IdType

@Entity(tableName = "Seller")
data class SellerEntity(
    val idNum: String,
    @PrimaryKey val invoiceId: Long,
    val idType: IdType?,
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
                name = "EcoMarket Food 12312312312312312312213213213213212131231231231232131212321 12312312312312312312213213213213212131231231231232131212321",
                address = "Rruga Fortuzi Rruga Fortuzi Rruga Fortuzi Rruga Fortuzi Rruga Fortuzi Rruga Fortuzi",
                town = "Tirane",
                country = "ALB",
            )
        }
    }
}