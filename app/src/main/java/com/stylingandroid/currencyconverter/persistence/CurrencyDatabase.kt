package com.stylingandroid.currencyconverter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ExchangeRateEntity::class, BalanceEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BigDecimalConverters::class,
    CurrencyConverters::class,
    DateTimeConverters::class,
    InstantConverters::class
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun conversionsDao(): CurrencyDao
}
