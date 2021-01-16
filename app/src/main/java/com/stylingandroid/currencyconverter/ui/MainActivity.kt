package com.stylingandroid.currencyconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stylingandroid.currencyconverter.R
import com.stylingandroid.currencyconverter.repository.CurrencyRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: CurrencyRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        const val INTERVAL = 15L
    }
}
