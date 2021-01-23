package com.stylingandroid.currencyconverter.net

import com.stylingandroid.currencyconverter.BuildConfig
import com.stylingandroid.currencyconverter.core.CurrencyProvider
import com.stylingandroid.currencyconverter.domain.Balance
import com.stylingandroid.currencyconverter.domain.ExchangeRate
import java.time.Instant
import java.util.Currency
import javax.inject.Inject

class TransferwiseProvider @Inject constructor(
    private val service: TransferwiseApiService
) : CurrencyProvider {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun exchangeRates(from: Currency, to: Currency): ExchangeRate? =
        try {
            service.rates(from.currencyCode, to.currencyCode).first().toDomainObject()
        } catch (throwable: Throwable) {
            null
        }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun balance(currency: Currency): Balance? =
        try {
            service.balances(BuildConfig.TRANSFERWISE_PROFILE_ID)
                .first()
                .balances
                .filter {
                    it.currency == currency.currencyCode
                }
                .map {
                    Balance(
                        currency = currency,
                        timestamp = Instant.now(),
                        balance = it.amount.value
                    )
                }
                .first()
        } catch (throwable: Throwable) {
            null
        }
}
