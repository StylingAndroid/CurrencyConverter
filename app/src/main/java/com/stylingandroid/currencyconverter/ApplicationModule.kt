package com.stylingandroid.currencyconverter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Currency
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    const val FROM_CURRENCY = "FROM_CURRENCY"
    const val TO_CURRENCY = "TO_CURRENCY"

    @Provides
    @Named(FROM_CURRENCY)
    fun provideFromCurrency(): Currency =
        Currency.getInstance("USD")

    @Provides
    @Named(TO_CURRENCY)
    fun provideToCurrency(): Currency =
        Currency.getInstance("GBP")

    @Provides
    fun providesApplicationCoroutineScope(@ApplicationContext context: Context) =
        (context as CurrencyConverterApplication).applicationScope

    @Provides
    fun providesProcessLifecycleOwner(): LifecycleOwner = ProcessLifecycleOwner.get()
}
