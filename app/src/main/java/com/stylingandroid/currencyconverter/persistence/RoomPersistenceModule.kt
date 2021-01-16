package com.stylingandroid.currencyconverter.persistence

import android.content.Context
import androidx.room.Room
import com.stylingandroid.currencyconverter.core.CurrencyPersistence
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomPersistenceModule {

    @Provides
    fun providesRoomConversionsDatabase(@ApplicationContext context: Context): CurrencyDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            CurrencyDatabase::class.java,
            "conversions"
        ).build()

    @Provides
    fun provideConversionsDoa(database: CurrencyDatabase): CurrencyDao = CurrencyDao_Impl(database)

    @Provides
    fun providesConversionsPersistence(impl: RoomCurrencyPersistence): CurrencyPersistence = impl
}
