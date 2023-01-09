package com.jeton.twentyleke.core.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.jeton.twentyleke.core.data.model.adapter.IdType
import com.jeton.twentyleke.core.data.model.adapter.InvoiceType
import com.jeton.twentyleke.core.data.model.adapter.TypeCode
import com.squareup.moshi.Json
import java.time.LocalDateTime

data class Invoice(
    @Json(name = "id") @PrimaryKey val id: Long?,
    @Json(name = "iic") @ColumnInfo(name = "iic") val iic: String?,
    @Json(name = "totalPrice") @ColumnInfo(name = "total_price") val totalPrice: Double?,
    @Json(name = "invoiceOrderNumber") @ColumnInfo(name = "invoice_order_number") val invoiceOrderNumber: Double?,
    @Json(name = "businessUnit") @ColumnInfo(name = "business_unit") val businessUnit: String?,
    @Json(name = "cashRegister") @ColumnInfo(name = "cash_register") val cashRegister: String?,
    @Json(name = "issuerTaxNumber") @ColumnInfo(name = "issuer_tax_number") val issuerTaxNumber: String?,
    @Json(name = "dateTimeCreated") @ColumnInfo(name = "date_time_created") val dateTimeCreated: String?,
    @Json(name = "fic") @ColumnInfo(name = "fic") val fic: String?,
    @Json(name = "paymentMethod") @ColumnInfo(name = "payment_method") val paymentMethod: List<PaymentMethod>?,
    @Json(name = "seller") @Embedded val seller: Seller?,
    @Json(name = "items") @Embedded val items: List<Item>?,
    @Json(name = "invoiceType") @Embedded val invoiceType: InvoiceType?,
    @Json(name = "invoiceNumber") @ColumnInfo(name = "invoice_number") val invoiceNumber: String?,
    @Json(name = "totalPriceWithoutVAT") @ColumnInfo(name = "total_price_without_vat") val totalPriceWithoutVAT: Double?,
    @Json(name = "totalVATAmount") @ColumnInfo(name = "total_vat_amount") val totalVATAmount: Double?,
    @Json(name = "operatorCode") @ColumnInfo(name = "operator_code") val operatorCode: String?,
    @Json(name = "softwareCode") @ColumnInfo(name = "software_code") val softwareCode: String?,
    @Json(name = "iicSignature") @ColumnInfo(name = "iic_signature") val iicSignature: String?,
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getLocalDateTimeCreated(): LocalDateTime {
        return LocalDateTime.parse(dateTimeCreated)
    }

    companion object {
        fun mock(): Invoice {
            val paymentMethod = listOf<PaymentMethod>(
                PaymentMethod(
                    by = null,
                    type = "Banknotes and coins",
                    amount = 344,
                    typeCode = TypeCode.BANKNOTE
                )
            )
            val items = listOf<Item>(
                Item(
                    id = 1527326185,
                    name = "COCA COLA 900 ML (6)",
                    code = "1",
                    unit = "cope",
                    quantity = 1.0,
                    unitPriceBeforeVat = 70.83,
                    unitPriceAfterVat = 85.0,
                    priceBeforeVat = 70.83,
                    vatRate = 20.0,
                    vatAmount = 14.17,
                    priceAfterVat = 85.0
                ), Item(
                    id = 1527326186,
                    name = "TWIX X -TRA SINGLE 85 GR (30)",
                    code = "1",
                    unit = "cope",
                    quantity = 1.0,
                    unitPriceBeforeVat = 70.83,
                    unitPriceAfterVat = 85.0,
                    priceBeforeVat = 70.83,
                    vatRate = 20.0,
                    vatAmount = 14.17,
                    priceAfterVat = 85.0
                ), Item(
                    id = 1527326187,
                    name = "BOUNTY 57 GR (24)",
                    code = "1",
                    unit = "cope",
                    quantity = 1.0,
                    unitPriceBeforeVat = 58.33,
                    unitPriceAfterVat = 70.0,
                    priceBeforeVat = 58.33,
                    vatRate = 20.0,
                    vatAmount = 11.67,
                    priceAfterVat = 70.0
                ), Item(
                    id = 1527326188,
                    name = "KIT KAT DARK 70 % 41.5 G XE(24)",
                    code = "1",
                    unit = "cope",
                    quantity = 1.0,
                    unitPriceBeforeVat = 74.17,
                    unitPriceAfterVat = 89.0,
                    priceBeforeVat = 74.17,
                    vatRate = 20.0,
                    vatAmount = 14.83,
                    priceAfterVat = 89.0
                ), Item(
                    id = 1527326189,
                    name = "QESE E VOGEL 2022",
                    code = "1",
                    unit = "cope",
                    quantity = 1.0,
                    unitPriceBeforeVat = 12.5,
                    unitPriceAfterVat = 15.0,
                    priceBeforeVat = 12.5,
                    vatRate = 20.0,
                    vatAmount = 2.5,
                    priceAfterVat = 15.0
                )
            )

            return Invoice(
                id = 616388310,
                iic = "5F02DA2E7197E0DB1D604F3A58233421",
                totalPrice = 344.0,
                invoiceOrderNumber = 773.0,
                businessUnit = "ck746kz097",
                cashRegister = "aw268av717",
                issuerTaxNumber = "K91312511U",
                dateTimeCreated = "2022-10-22T18:17:51.00",
                fic = "92ade238-f852-4d99-a827-10cc32eef9cd",
                paymentMethod = paymentMethod,
                seller = Seller(
                    idType = IdType.NUIS,
                    idNum = "K91312511U",
                    name = "EcoMarket Food",
                    address = "Rruga Fortuzi",
                    town = "Tirane",
                    country = "ALB"
                ),
                items = items,
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