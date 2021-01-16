package com.stylingandroid.currencyconverter.net

import retrofit2.http.GET
import retrofit2.http.Query

interface TransferwiseApiService {

    @GET("/v1/rates")
    suspend fun rates(
        @Query("source") source: String,
        @Query("target") target: String
    ): List<TransferwiseRatesResponse>

    @GET("/v1/borderless-accounts")
    suspend fun balances(
        @Query("profileId") profileId: String
    ): List<TransferwiseBalancesResponse>
}
