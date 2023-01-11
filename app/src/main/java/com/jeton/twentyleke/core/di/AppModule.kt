package com.jeton.twentyleke.core.di

import android.content.Context
import androidx.room.Room
import com.jeton.twentyleke.BuildConfig
import com.jeton.twentyleke.core.data.model.adapter.IdTypeAdapter
import com.jeton.twentyleke.core.data.model.adapter.InvoiceTypeAdapter
import com.jeton.twentyleke.core.data.model.adapter.TypeCodeAdapter
import com.jeton.twentyleke.core.data.network.service.InvoiceCheckClient
import com.jeton.twentyleke.core.data.network.service.InvoiceCheckClientImpl
import com.jeton.twentyleke.core.data.network.service.InvoiceCheckService
import com.jeton.twentyleke.core.data.persistance.TwentyLekeDatabase
import com.jeton.twentyleke.core.util.SharedPreferencesUtils
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// TODO: Move `BASE_URL` in `BuildConfig`
const val BASE_URL = "https://efiskalizimi-app.tatime.gov.al/invoice-check/api/"
const val DATABASE_NAME = "kuleta-db"

val appModule = module {
    single { provideSharedPreferences(androidContext()) }
    single { provideInvoiceDao(androidContext(), DATABASE_NAME) }
    single { provideOkHttpClient() }
    single { provideRetrofit(get<OkHttpClient>(), BASE_URL) }
    single { provideInvoiceCheckService(get<Retrofit>()) }
    single<InvoiceCheckClient> { provideInvoiceClientImpl(get<InvoiceCheckService>()) }
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient.Builder()
    .build()

private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    val moshi = Moshi.Builder()
        .add(InvoiceTypeAdapter())
        .add(TypeCodeAdapter())
        .add(IdTypeAdapter())
        .build()

    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
}

private fun provideInvoiceClientImpl(invoiceCheckService: InvoiceCheckService): InvoiceCheckClientImpl =
    InvoiceCheckClientImpl(invoiceCheckService)

private fun provideInvoiceCheckService(retrofit: Retrofit): InvoiceCheckService =
    retrofit.create(InvoiceCheckService::class.java)

private fun provideSharedPreferences(context: Context) =
    SharedPreferencesUtils.defaultPreferences(context)

private fun provideInvoiceDao(context: Context, databaseName: String) =
    Room.databaseBuilder(context, TwentyLekeDatabase::class.java, databaseName)
        .build()
        .invoiceDao()