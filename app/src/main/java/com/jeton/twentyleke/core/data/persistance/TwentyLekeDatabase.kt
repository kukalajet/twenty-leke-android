package com.jeton.twentyleke.core.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.data.model.Item
import com.jeton.twentyleke.core.data.model.PaymentMethod
import com.jeton.twentyleke.core.data.model.Seller
import com.jeton.twentyleke.core.data.persistance.dao.InvoiceDao

@Database(
    entities = [Invoice::class, Item::class, PaymentMethod::class, Seller::class],
    version = 1
)
abstract class TwentyLekeDatabase : RoomDatabase() {
    abstract fun invoiceDao(): InvoiceDao
}