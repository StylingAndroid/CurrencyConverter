package com.stylingandroid.currencyconverter.net

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.stylingandroid.currencyconverter.BuildConfig
import com.stylingandroid.currencyconverter.core.CurrencyProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object TransferwiseApiModule {

    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .header("Authorization", "Bearer ${BuildConfig.TRANSFERWISE_API_KEY}")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

    @Provides
    fun providesConverterFactory(): Converter.Factory =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(BigDecimalAdapter)
                .build()
        )

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.TRANSFERWISE_BASE_URL)
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    fun providesTransferwiseApiService(retrofit: Retrofit): TransferwiseApiService =
        retrofit.create(TransferwiseApiService::class.java)

    @Provides
    fun providesNetworkConversionsProvider(
        impl: TransferwiseProvider
    ): CurrencyProvider = impl
}
