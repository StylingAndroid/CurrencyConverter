package com.stylingandroid.currencyconverter.repository

import com.stylingandroid.currencyconverter.ApplicationModule
import com.stylingandroid.currencyconverter.core.CurrencyPersistence
import com.stylingandroid.currencyconverter.core.CurrencyProvider
import com.stylingandroid.currencyconverter.domain.Balance
import com.stylingandroid.currencyconverter.domain.ExchangeRate
import java.time.Duration
import java.time.Instant
import java.util.Currency
import javax.inject.Inject
import javax.inject.Named

class CurrencyRepository @Inject constructor(
    private val persistence: CurrencyPersistence,
    private val provider: CurrencyProvider,
    @Named(ApplicationModule.FROM_CURRENCY) private val from: Currency,
    @Named(ApplicationModule.TO_CURRENCY) private val to: Currency
) {

    suspend fun exchangeRates(validity: Duration): ExchangeRate {
        persistence.purgeExchangeRates(Instant.now().minus(validity))
        return persistence.loadExchangeRates() ?: provider.exchangeRates(from, to).also {
            persistence.saveExchangeRates(it)
        }
    }

    suspend fun balance(validity: Duration): Balance {
        persistence.purgeBalance(Instant.now().minus(validity))
        return persistence.loadBalance() ?: provider.balance(from).also {
            persistence.saveBalance(it)
        }
    }
}
