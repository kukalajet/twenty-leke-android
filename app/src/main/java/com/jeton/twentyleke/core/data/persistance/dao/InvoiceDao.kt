package com.jeton.twentyleke.core.data.persistance.dao

import androidx.room.*
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.data.model.entity.HeaderEntity
import com.jeton.twentyleke.core.data.model.entity.ItemEntity
import com.jeton.twentyleke.core.data.model.entity.PaymentMethodEntity
import com.jeton.twentyleke.core.data.model.entity.SellerEntity

@Dao
interface InvoiceDao {

    @Query("SELECT * FROM header")
    fun getAllInvoices(): List<Invoice>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInvoice(
        header: HeaderEntity?,
        seller: SellerEntity?,
        items: List<ItemEntity>?,
        paymentMethods: List<PaymentMethodEntity>?,
    )

    @Delete
    fun deleteInvoice(
        header: HeaderEntity,
        seller: SellerEntity,
        items: List<ItemEntity>,
        paymentMethods: List<PaymentMethodEntity>,
    )
}