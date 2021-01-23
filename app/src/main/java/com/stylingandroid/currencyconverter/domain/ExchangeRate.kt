package com.stylingandroid.currencyconverter.domain

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.util.Currency

data class ExchangeRate(
    val from: Currency,
    val to: Currency,
    val date: LocalDateTime,
    override val timestamp: Instant,
    val rate: BigDecimal
) : Timestamped(timestamp)
