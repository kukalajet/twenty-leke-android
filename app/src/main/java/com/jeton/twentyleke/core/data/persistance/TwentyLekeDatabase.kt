package com.jeton.twentyleke.core.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeton.twentyleke.core.data.model.entity.HeaderEntity
import com.jeton.twentyleke.core.data.model.entity.ItemEntity
import com.jeton.twentyleke.core.data.model.entity.PaymentMethodEntity
import com.jeton.twentyleke.core.data.model.entity.SellerEntity
import com.jeton.twentyleke.core.data.persistance.dao.InvoiceDao

@Database(
    entities = [HeaderEntity::class, ItemEntity::class, PaymentMethodEntity::class, SellerEntity::class],
    version = 1
)
abstract class TwentyLekeDatabase : RoomDatabase() {
    abstract fun invoiceDao(): InvoiceDao
}