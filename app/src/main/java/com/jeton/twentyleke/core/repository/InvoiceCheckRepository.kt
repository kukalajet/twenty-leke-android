package com.jeton.twentyleke.core.repository

import android.content.SharedPreferences
import com.jeton.twentyleke.core.data.model.Invoice
import com.jeton.twentyleke.core.data.network.service.InvoiceCheckClient
import com.jeton.twentyleke.core.util.SharedPreferencesUtils.get
import com.jeton.twentyleke.core.util.SharedPreferencesUtils.set
import com.squareup.moshi.Moshi
import retrofit2.Response

class InvoiceCheckRepository(
    private val sharedPreferences: SharedPreferences,
    private val invoiceCheckClient: InvoiceCheckClient
) {

    suspend fun getInvoice(
        iic: String,
        dateTimeCreated: String,
        tin: String,
    ): Response<Invoice> {
        val response = invoiceCheckClient.fetchInvoice(iic, dateTimeCreated, tin)
        if (response.isSuccessful) {
            val invoice = response.body()
            cacheInvoice(invoice)
        }

        return response
    }

    private fun cacheInvoice(invoice: Invoice?) {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Invoice::class.java)
        val json = adapter.toJson(invoice)
        sharedPreferences[CACHED_INVOICE_KEY] = json
    }

    fun getCachedInvoice(): Invoice? {
        val json = sharedPreferences[CACHED_INVOICE_KEY, ""]
        if (json.isEmpty()) return null
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Invoice::class.java)
        return adapter.fromJson(json)
    }

    fun clearCachedInvoice(): Boolean {
        val editor = sharedPreferences.edit()
        return editor.remove(CACHED_INVOICE_KEY).commit()
    }

    companion object {
        const val CACHED_INVOICE_KEY = "cached_invoice_key"
    }
}