package com.jeton.twentyleke.core.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeton.twentyleke.core.data.model.adapter.TypeCode

@Entity(tableName = "PaymentMethod")
data class PaymentMethodEntity(
    @PrimaryKey val invoiceId: Long,
    val by: Long?,
    val type: String?,
    val amount: Double?,
    val typeCode: TypeCode?
) {
    companion object {
        fun getMockedSamples(invoiceId: Long): List<PaymentMethodEntity> {
            return listOf(
                PaymentMethodEntity(
                    invoiceId = invoiceId,
                    by = null,
                    type = "Banknotes and coins",
                    amount = 344.0,
                    typeCode = TypeCode.BANKNOTE
                )
            )

        }
    }
}