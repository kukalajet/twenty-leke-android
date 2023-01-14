package com.jeton.twentyleke.core.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.jeton.twentyleke.core.data.model.entity.HeaderEntity
import com.jeton.twentyleke.core.data.model.entity.ItemEntity
import com.jeton.twentyleke.core.data.model.entity.PaymentMethodEntity
import com.jeton.twentyleke.core.data.model.entity.SellerEntity

data class Invoice(
    @Embedded val header: HeaderEntity?,
    @Relation(parentColumn = "invoiceId", entityColumn = "invoiceId")
    val seller: SellerEntity?,
    @Relation(parentColumn = "invoiceId", entityColumn = "invoiceId")
    val items: List<ItemEntity>?,
    @Relation(parentColumn = "invoiceId", entityColumn = "invoiceId")
    val paymentMethods: List<PaymentMethodEntity>?
) {
    companion object {
        fun getMockedSample(): Invoice {
            val header = HeaderEntity.getMockedSample()
            val invoiceId = header.invoiceId
            val seller = SellerEntity.getMockedSample(invoiceId!!)
            val items = ItemEntity.getMockedSamples(invoiceId)
            val paymentMethods = PaymentMethodEntity.getMockedSamples(invoiceId)
            return Invoice(header, seller, items, paymentMethods)
        }
    }
}