package com.jeton.twentyleke.core.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeton.twentyleke.core.data.model.adapter.InvoiceType
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "Header")
data class HeaderEntity(
    @PrimaryKey val invoiceId: Long?,
    val iic: String?,
    val totalPrice: Double?,
    val invoiceOrderNumber: Double?,
    val businessUnit: String?,
    val cashRegister: String?,
    val issuerTaxNumber: String?,
    val dateTimeCreated: Long?,
    val fic: String?,
    val invoiceType: InvoiceType?,
    val invoiceNumber: String?,
    val totalPriceWithoutVAT: Double?,
    val totalVATAmount: Double?,
    val operatorCode: String?,
    val softwareCode: String?,
    val iicSignature: String?,
) {

    fun getLocalDateTimeCreated(): LocalDateTime {
        return LocalDateTime.ofInstant(
            Instant.ofEpochMilli(dateTimeCreated!!),
            TimeZone.getDefault().toZoneId()
        )
    }

    companion object {
        fun getMockedSample(): HeaderEntity {
            return HeaderEntity(
                invoiceId = 1,
                iic = "5F02DA2E7197E0DB1D604F3A58233421",
                totalPrice = 344.0,
                invoiceOrderNumber = 773.0,
                businessUnit = "ck746kz097",
                cashRegister = "aw268av717",
                issuerTaxNumber = "K91312511U",
                dateTimeCreated = 1673909570493,
                fic = "92ade238-f852-4d99-a827-10cc32eef9cd",
                invoiceType = InvoiceType.CASH,
                invoiceNumber = "773 / 2022 / aw268av717",
                totalPriceWithoutVAT = 286.66,
                totalVATAmount = 57.34,
                operatorCode = "qy209st538",
                softwareCode = "jb125nu757",
                iicSignature = "16 C57A0E07A834A4908DA5743DAA9184796830396C99DC54F7C027C3FC403FB7BF87DA54EDAE34C1EC97D2027D5AA89D328C35A351658CC2B43C7CCAE1AAF1BAF8ACDEC14755339C586172B1575CD595BE10EBF1C4E83DA02E2E4A643B334AE961562583A696FA26F25C51FA9332C3B6711288833480C3F8D362B8111C65C4F3F2439D532D2C9475DB91DED83432F3871E4090DCC3B60F1AC080E399BCC6E37DEF04361D3ED54404CE04DB1C0F0FB48E7C88E1F860409DD8672A5CF8AC9EB58B54C6601379D80E7CCB9B35B101826161508C9D87384F73C0E7F4A6543CA457624CA76B4FD7A3654799606D8CFB29B16B314C150637AE3ABB896A8BDA3C59C771"
            )
        }
    }
}