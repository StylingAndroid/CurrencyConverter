package com.stylingandroid.currencyconverter.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stylingandroid.currencyconverter.domain.Balance
import java.math.BigDecimal
import java.time.Instant
import java.util.Currency

@Entity
data class BalanceEntity(
    @PrimaryKey val currencyCode: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "balance") val balance: String
) {

    fun toDomainObject(): Balance =
        Balance(
            currency = Currency.getInstance(currencyCode),
            timestamp = Instant.ofEpochMilli(timestamp),
            balance = BigDecimal(balance)
        )

    companion object {
        fun fromDomainObject(balance: Balance): BalanceEntity =
            BalanceEntity(
                currencyCode = balance.currency.currencyCode,
                timestamp = balance.timestamp.toEpochMilli(),
                balance = balance.balance.toString()
            )
    }
}
