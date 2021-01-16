package com.stylingandroid.currencyconverter.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM ExchangeRateEntity")
    fun getExchangeRates(): List<ExchangeRateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExchangeRatesEntity(exchangeRate: ExchangeRateEntity): Long

    @Query("DELETE FROM ExchangeRateEntity")
    fun deleteAllExchangeRates()

    @Query("DELETE FROM ExchangeRateEntity WHERE timestamp <= :expiry")
    fun purgeExchangeRates(expiry: Long)

    @Query("SELECT * from BalanceEntity")
    fun getBalances(): List<BalanceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBalanceEntity(balance: BalanceEntity): Long

    @Query("DELETE FROM BalanceEntity")
    fun deleteAllEBalances()

    @Query("DELETE FROM BalanceEntity WHERE timestamp <= :expiry")
    fun purgeBalance(expiry: Long)
}
