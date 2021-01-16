package com.stylingandroid.currencyconverter.net

import com.squareup.moshi.Json
import com.stylingandroid.currencyconverter.domain.ExchangeRate
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import java.time.format.DateTimeFormatterBuilder
import java.util.Currency
import java.util.Locale

data class TransferwiseRatesResponse(
    @Json(name = "rate") val rate: BigDecimal,
    @Json(name = "source") val source: String,
    @Json(name = "target") val target: String,
    @Json(name = "time") val time: String
) {
    fun toDomainObject(): ExchangeRate =
        ExchangeRate(
            from = Currency.getInstance(source),
            to = Currency.getInstance(target),
            rate = rate,
            date = LocalDateTime.parse(time, TRANSFERWISE_DATE_TIME_FORMAT),
            timestamp = Instant.now()
        )
}

var TRANSFERWISE_DATE_TIME_FORMAT: DateTimeFormatter = DateTimeFormatterBuilder()
    .parseCaseInsensitive()
    .append(ISO_LOCAL_DATE)
    .appendLiteral('T')
    .append(ISO_LOCAL_TIME)
    .appendOffset("+HHmm", "+0000")
    .toFormatter(Locale.getDefault())
