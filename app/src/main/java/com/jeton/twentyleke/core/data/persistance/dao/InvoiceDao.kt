package com.jeton.twentyleke.core.data.persistance.dao

import androidx.room.Dao
import androidx.room.Query
import com.jeton.twentyleke.core.data.model.Invoice

@Dao
interface InvoiceDao {
    @Query("SELECT * FROM header")
    fun getAllInvoices(): List<Invoice>
}