package com.stylingandroid.currencyconverter.repository

import com.stylingandroid.currencyconverter.ApplicationModule
import com.stylingandroid.currencyconverter.core.CurrencyPersistence
import com.stylingandroid.currencyconverter.core.CurrencyProvider
import com.stylingandroid.currencyconverter.domain.Balance
import com.stylingandroid.currencyconverter.domain.ExchangeRate
import com.stylingandroid.currencyconverter.domain.Timestamped
import java.time.Duration
import java.time.Instant
import java.util.Currency
import javax.inject.Inject
import javax.inject.Named
import timber.log.Timber

class CurrencyRepository @Inject constructor(
    private val persistence: CurrencyPersistence,
    private val provider: CurrencyProvider,
    @Named(ApplicationModule.FROM_CURRENCY) private val from: Currency,
    @Named(ApplicationModule.TO_CURRENCY) private val to: Currency
) {

    suspend fun exchangeRates(validity: Duration): ExchangeRate? {
        val persisted = persistence.loadExchangeRates()
        return loadAndPurge(
            value = persisted,
            validity = validity,
            purger = persistence::purgeExchangeRates,
            saver = persistence::saveExchangeRates
        ) {
            provider.exchangeRates(from, to)
        }
    }

    suspend fun balance(validity: Duration): Balance? {
        val persisted = persistence.loadBalance()
        return loadAndPurge(
            value = persisted,
            validity = validity,
            purger = persistence::purgeBalance,
            saver = persistence::saveBalance
        ) {
            provider.balance(from)
        }
    }

    private suspend fun <T : Timestamped> loadAndPurge(
        value: T?,
        validity: Duration,
        purger: suspend () -> Unit,
        saver: suspend (T) -> Unit,
        retriever: suspend () -> T?
    ): T? {
        return if (value?.timestamp?.isAfter(Instant.now().minus(validity)) == true) {
            value
        } else {
            retriever()?.also { newValue ->
                purger()

                saver(newValue)
            } ?: run {
                Timber
                    .tag("CurrencyRepository")
                    .d("Retrieve failed, so using expired, persisted value")
                value
            }
        }
    }
}
