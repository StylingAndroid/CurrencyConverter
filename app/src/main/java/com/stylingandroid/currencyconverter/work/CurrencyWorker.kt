package com.stylingandroid.currencyconverter.work

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.stylingandroid.currencyconverter.repository.CurrencyRepository
import com.stylingandroid.currencyconverter.ui.appwidget.CurrencyAppWidgetProvider
import java.text.NumberFormat
import java.time.Duration
import java.util.Currency
import java.util.Locale
import timber.log.Timber

class CurrencyWorker @WorkerInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workParams: WorkerParameters,
    private val repository: CurrencyRepository
) : CoroutineWorker(appContext, workParams) {

    companion object {
        private const val FIFTEEN = 15L
    }

    private var ratesValidity = Duration.ofMinutes(FIFTEEN)
    private var balanceValidity = Duration.ofHours(1L)

    @Suppress("TooGenericExceptionCaught")
    override suspend fun doWork(): Result =
        try {
            val rate = repository.exchangeRates(ratesValidity)
            val balance = repository.balance(balanceValidity)
            val balanceString = balance?.balance?.let { balanceValue ->
                rate?.from?.let { fromCurrency ->
                    currencyFormat(fromCurrency).format(balanceValue)
                }
            }
            val convertedString = balance?.balance?.let { balanceValue ->
                rate?.let { rateValue ->
                    currencyFormat(rateValue.to).format(rateValue.rate.multiply(balanceValue))
                }
            }
            Timber.d("Worker ran: $rate $balance $convertedString")
            appContext.sendBroadcast(
                Intent(appContext, CurrencyAppWidgetProvider::class.java).apply {
                    action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                    putExtra(
                        AppWidgetManager.EXTRA_APPWIDGET_IDS,
                        AppWidgetManager.getInstance(appContext).getAppWidgetIds(
                            ComponentName(appContext, CurrencyAppWidgetProvider::class.java)
                        )
                    )

                    putExtra("BALANCE", balanceString)
                    putExtra("CONVERTED", convertedString)
                }
            )
            Result.success()
        } catch (error: Throwable) {
            Timber.w(error, "Worker failed")
            Result.failure()
        }

    private fun currencyFormat(requiredCurrency: Currency) =
        NumberFormat.getCurrencyInstance(Locale.US).apply {
            currency = requiredCurrency
        }
}
