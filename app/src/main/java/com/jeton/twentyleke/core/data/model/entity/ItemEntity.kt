package com.jeton.twentyleke.core.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "Item")
data class ItemEntity(
    @Json(name = "id") @PrimaryKey val itemId: Long?,
    @Json(name = "name") val name: String?,
    @Json(name = "code") val code: String?,
    @Json(name = "unit") val unit: String?,
    @Json(name = "quantity") val quantity: Double?,
    @Json(name = "unitPriceBeforeVat") val unitPriceBeforeVat: Double?,
    @Json(name = "unitPriceAfterVat") val unitPriceAfterVat: Double?,
    @Json(name = "priceBeforeVat") val priceBeforeVat: Double?,
    @Json(name = "vatRate") val vatRate: Double?,
    @Json(name = "vatAmount") val vatAmount: Double?,
    @Json(name = "priceAfterVat") val priceAfterVat: Double?,
) {
    companion object {
        fun getMockedSamples(): List<ItemEntity> {
            return listOf(
                ItemEntity(
                    itemId = 1527326185,
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
                ), ItemEntity(
                    itemId = 1527326186,
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
                ), ItemEntity(
                    itemId = 1527326187,
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
                ), ItemEntity(
                    itemId = 1527326188,
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
                ), ItemEntity(
                    itemId = 1527326189,
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
                ),
                ItemEntity(
                    itemId = 1527326185,
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
                ), ItemEntity(
                    itemId = 1527326186,
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
                ), ItemEntity(
                    itemId = 1527326187,
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
                ), ItemEntity(
                    itemId = 1527326188,
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
                ), ItemEntity(
                    itemId = 1527326189,
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
                ),
                ItemEntity(
                    itemId = 1527326185,
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
                ), ItemEntity(
                    itemId = 1527326186,
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
                ), ItemEntity(
                    itemId = 1527326187,
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
                ), ItemEntity(
                    itemId = 1527326188,
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
                ), ItemEntity(
                    itemId = 1527326189,
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
                ),
                ItemEntity(
                    itemId = 1527326185,
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
                ), ItemEntity(
                    itemId = 1527326186,
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
                ), ItemEntity(
                    itemId = 1527326187,
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
                ), ItemEntity(
                    itemId = 1527326188,
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
                ), ItemEntity(
                    itemId = 1527326189,
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
                ), ItemEntity(
                    itemId = 1527326185,
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
                ), ItemEntity(
                    itemId = 1527326186,
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
                ), ItemEntity(
                    itemId = 1527326187,
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
                ), ItemEntity(
                    itemId = 1527326188,
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
                ), ItemEntity(
                    itemId = 1527326189,
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
        }
    }
}
