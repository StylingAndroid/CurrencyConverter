package com.stylingandroid.currencyconverter.persistence

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Currency

class BigDecimalConverters {

    @TypeConverter
    fun fromString(value: String): BigDecimal =
        BigDecimal(value)

    @TypeConverter
    fun toString(bigDecimal: BigDecimal): String =
        bigDecimal.toString()
}

class CurrencyConverters {

    @TypeConverter
    fun fromString(currencyCode: String): Currency =
        Currency.getInstance(currencyCode)

    @TypeConverter
    fun toString(currency: Currency): String =
        currency.currencyCode
}

class DateTimeConverters {

    @TypeConverter
    fun fromString(dateTime: String): LocalDateTime =
        LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    @TypeConverter
    fun toString(dateTime: LocalDateTime): String =
        dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}

class InstantConverters {

    @TypeConverter
    fun fromEpocMillis(epocMillis: Long): Instant =
        Instant.ofEpochMilli(epocMillis)

    @TypeConverter
    fun toEpocMillis(instant: Instant): Long =
        instant.toEpochMilli()
}
