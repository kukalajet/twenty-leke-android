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
    suspend fun getAllInvoices(): List<Invoice>

    @Query("SELECT * FROM header WHERE invoiceId = :invoiceId")
    suspend fun getInvoiceById(invoiceId: Long): Invoice

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeader(header: HeaderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeller(seller: SellerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(items: List<ItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaymentMethods(paymentMethods: List<PaymentMethodEntity>)

    @Delete
    fun deleteHeader(header: HeaderEntity)

    @Delete
    fun deleteSeller(seller: SellerEntity)

    @Delete
    fun deleteItems(items: List<ItemEntity>)

    @Delete
    fun deletePaymentMethods(paymentMethods: List<PaymentMethodEntity>)

    suspend fun insertInvoice(invoice: Invoice) {
        invoice.header?.let { it -> insertHeader(it) }
        invoice.seller?.let { it -> insertSeller(it) }
        invoice.items?.let { it -> insertItems(it) }
        invoice.paymentMethods?.let { insertPaymentMethods(it) }
    }

    suspend fun deleteInvoice(invoice: Invoice) {
        invoice.header?.let { it -> deleteHeader(it) }
        invoice.seller?.let { it -> deleteSeller(it) }
        invoice.items?.let { it -> deleteItems(it) }
        invoice.paymentMethods?.let { deletePaymentMethods(it) }
    }
}