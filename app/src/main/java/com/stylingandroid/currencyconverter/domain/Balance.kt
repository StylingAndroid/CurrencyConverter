package com.stylingandroid.currencyconverter.domain

import java.math.BigDecimal
import java.time.Instant
import java.util.Currency

data class Balance(
    val currency: Currency,
    val timestamp: Instant,
    val balance: BigDecimal
)
