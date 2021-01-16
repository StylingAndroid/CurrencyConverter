package com.stylingandroid.currencyconverter.net

import com.squareup.moshi.Json
import java.math.BigDecimal

data class TransferwiseBalancesResponse(
    @Json(name = "id") val id: String,
    @Json(name = "profileId") val profileId: String,
    @Json(name = "recipientId") val recipientId: String,
    @Json(name = "creationTime") val creationTime: String,
    @Json(name = "modificationTime") val modificationTime: String,
    @Json(name = "active") val active: Boolean,
    @Json(name = "eligible") val eligible: Boolean,
    @Json(name = "balances") val balances: List<TransferwiseBalance>
)

data class TransferwiseBalance(
    @Json(name = "balanceType") val balanceType: String,
    @Json(name = "currency") val currency: String,
    @Json(name = "amount") val amount: TransferwiseAmount,
    @Json(name = "reservedAmount") val reservedAmount: TransferwiseAmount,
    @Json(name = "bankDetails") val bankDetails: TransferwiseBankDetails?
)

data class TransferwiseAmount(
    @Json(name = "value") val value: BigDecimal,
    @Json(name = "currency") val currency: String
)

data class TransferwiseBankDetails(
    @Json(name = "id") val id: String,
    @Json(name = "currency") val currency: String,
    @Json(name = "bankCode") val bankCode: String,
    @Json(name = "accountNumber") val accountNumber: String,
    @Json(name = "swift") val swift: String?,
    @Json(name = "iban") val iban: String?,
    @Json(name = "bankName") val bankName: String,
    @Json(name = "accountHolderName") val accountHolderName: String,
    @Json(name = "bankAddress") val bankAddress: TransferwiseAddress
)

data class TransferwiseAddress(
    @Json(name = "addressFirstLine") val addressFirstLine: String,
    @Json(name = "postCode") val postCode: String,
    @Json(name = "city") val city: String,
    @Json(name = "country") val country: String,
    @Json(name = "stateCode") val stateCode: String?,
)
