package com.stylingandroid.currencyconverter.persistence

import com.stylingandroid.currencyconverter.core.CurrencyPersistence
import com.stylingandroid.currencyconverter.domain.Balance
import com.stylingandroid.currencyconverter.domain.ExchangeRate
import java.time.Instant
import javax.inject.Inject

class RoomCurrencyPersistence @Inject constructor(
    database: CurrencyDatabase,
    private val dao: CurrencyDao = database.conversionsDao()
) : CurrencyPersistence {

    override suspend fun saveExchangeRates(exchangeRate: ExchangeRate) {
        dao.deleteAllExchangeRates()
        dao.insertExchangeRatesEntity(
            ExchangeRateEntity.fromDomainObject(exchangeRate)
        )
    }

    override suspend fun loadExchangeRates(): ExchangeRate? =
        dao.getExchangeRates().firstOrNull()?.toDomainObject()

    override suspend fun purgeExchangeRates(expiry: Instant) {
        dao.purgeExchangeRates(expiry.toEpochMilli())
    }

    override suspend fun saveBalance(balance: Balance) {
        dao.deleteAllEBalances()
        dao.insertBalanceEntity(BalanceEntity.fromDomainObject(balance))
    }

    override suspend fun loadBalance(): Balance? =
        dao.getBalances().firstOrNull()?.toDomainObject()

    override suspend fun purgeBalance(expiry: Instant) {
        dao.purgeBalance(expiry.toEpochMilli())
    }
}
