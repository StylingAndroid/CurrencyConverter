package com.stylingandroid.currencyconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stylingandroid.currencyconverter.databinding.ActivityMainBinding
import com.stylingandroid.currencyconverter.update.UpdateScheduler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var updateScheduler: UpdateScheduler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            Timber.d("Button clicked")
            updateScheduler.enqueueOneTimeWorker()
        }
    }
}
