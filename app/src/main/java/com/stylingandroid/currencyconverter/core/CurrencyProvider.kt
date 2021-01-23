package com.stylingandroid.currencyconverter.core

import com.stylingandroid.currencyconverter.domain.Balance
import com.stylingandroid.currencyconverter.domain.ExchangeRate
import java.util.Currency

interface CurrencyProvider {

    suspend fun exchangeRates(from: Currency, to: Currency): ExchangeRate?

    suspend fun balance(currency: Currency): Balance?
}
