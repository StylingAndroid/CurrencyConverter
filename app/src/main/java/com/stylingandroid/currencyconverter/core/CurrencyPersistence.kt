package com.stylingandroid.currencyconverter.core

import com.stylingandroid.currencyconverter.domain.Balance
import com.stylingandroid.currencyconverter.domain.ExchangeRate

interface CurrencyPersistence {

    suspend fun saveExchangeRates(exchangeRate: ExchangeRate)

    suspend fun loadExchangeRates(): ExchangeRate?

    suspend fun purgeExchangeRates()

    suspend fun saveBalance(balance: Balance)

    suspend fun loadBalance(): Balance?

    suspend fun purgeBalance()
}
