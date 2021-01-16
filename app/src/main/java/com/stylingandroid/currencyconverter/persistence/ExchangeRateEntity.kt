package com.stylingandroid.currencyconverter.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.stylingandroid.currencyconverter.domain.ExchangeRate
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.util.Currency

@Entity(
    primaryKeys = ["from", "to"]
)
data class ExchangeRateEntity(
    @ColumnInfo(name = "from") val from: Currency,
    @ColumnInfo(name = "to") val to: Currency,
    @ColumnInfo(name = "date") val date: LocalDateTime,
    @ColumnInfo(name = "timestamp") val timestamp: Instant,
    @ColumnInfo(name = "rate") val rate: BigDecimal
) {

    fun toDomainObject(): ExchangeRate =
        ExchangeRate(
            from = from,
            to = to,
            date = date,
            timestamp = timestamp,
            rate = rate
        )

    companion object {
        fun fromDomainObject(exchangeRate: ExchangeRate) =
            ExchangeRateEntity(
                from = exchangeRate.from,
                to = exchangeRate.to,
                date = exchangeRate.date,
                timestamp = exchangeRate.timestamp,
                rate = exchangeRate.rate
            )
    }
}
