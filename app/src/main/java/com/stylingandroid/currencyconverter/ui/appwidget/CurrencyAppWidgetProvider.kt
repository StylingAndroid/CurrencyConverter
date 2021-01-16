package com.stylingandroid.currencyconverter.ui.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.stylingandroid.currencyconverter.R
import com.stylingandroid.currencyconverter.update.UpdateScheduler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import timber.log.Timber

@AndroidEntryPoint
class CurrencyAppWidgetProvider : AppWidgetProvider() {

    @Inject
    lateinit var updateScheduler: UpdateScheduler

    private var balance: String? = null
    private var converted: String? = null

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        Timber.d("OnEnabled")
        updateScheduler.startUpdates()
    }

    override fun onReceive(context: Context, intent: Intent) {
        balance = intent.getStringExtra("BALANCE")
        converted = intent.getStringExtra("CONVERTED")
        super.onReceive(context, intent)
    }

    override fun onUpdate(context: Context, widgetManager: AppWidgetManager, widgetIds: IntArray) {
        widgetIds.forEach { id ->
            Timber.d("onUpdate")
            val views = RemoteViews(context.packageName, R.layout.currency_widget).apply {
                balance?.also {
                    setTextViewText(R.id.balance, it)
                }
                converted?.also {
                    setTextViewText(R.id.converted, it)
                }
            }
            widgetManager.updateAppWidget(id, views)
        }
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        Timber.d("OnDisabled")
        updateScheduler.stopUpdates()
    }
}
