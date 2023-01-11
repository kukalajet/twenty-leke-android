package com.jeton.twentyleke.core.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.jeton.twentyleke.core.data.model.entity.HeaderEntity
import com.jeton.twentyleke.core.data.model.entity.ItemEntity
import com.jeton.twentyleke.core.data.model.entity.PaymentMethodEntity
import com.jeton.twentyleke.core.data.model.entity.SellerEntity

data class Invoice(
    @Embedded val header: HeaderEntity?,
    @Relation(parentColumn = "headerId", entityColumn = "sellerId")
    val seller: SellerEntity?,
    @Relation(parentColumn = "headerId", entityColumn = "itemId")
    val items: List<ItemEntity>?,
    @Relation(parentColumn = "headerId", entityColumn = "paymentMethodId")
    val paymentMethods: List<PaymentMethodEntity>?
) {
    companion object {
        fun getMockedSample(): Invoice {
            return Invoice(
                header = HeaderEntity.getMockedSample(),
                seller = SellerEntity.getMockedSample(),
                items = ItemEntity.getMockedSamples(),
                paymentMethods = PaymentMethodEntity.getMockedSamples(),
            )
        }
    }
}