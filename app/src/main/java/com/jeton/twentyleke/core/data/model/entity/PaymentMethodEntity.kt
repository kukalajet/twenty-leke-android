package com.jeton.twentyleke.core.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeton.twentyleke.core.data.model.adapter.TypeCode
import com.squareup.moshi.Json

@Entity(tableName = "PaymentMethod")
data class PaymentMethodEntity(
    @PrimaryKey(autoGenerate = true) var paymentMethodId: Int = 0,
    @Json(name = "by") val by: Long?,
    @Json(name = "type") val type: String?,
    @Json(name = "amount") val amount: Double?,
    @Json(name = "typeCode") val typeCode: TypeCode?
) {
    companion object {
        fun getMockedSamples(): List<PaymentMethodEntity> {
            return listOf(
                PaymentMethodEntity(
                    paymentMethodId = 1234,
                    by = null,
                    type = "Banknotes and coins",
                    amount = 344.0,
                    typeCode = TypeCode.BANKNOTE
                )
            )

        }
    }
}