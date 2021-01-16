package com.stylingandroid.currencyconverter.update

import androidx.datastore.core.DataStore
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.stylingandroid.currencyconverter.datastore.WorkerDetails
import com.stylingandroid.currencyconverter.work.CurrencyWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class UpdateScheduler @Inject constructor(
    private val workManager: WorkManager,
    private val workerDetailsDataStore: DataStore<WorkerDetails>,
    private val lifecycleOwner: LifecycleOwner
) {

    private val workerDetailsLiveData =
        workerDetailsDataStore.data.asLiveData(lifecycleOwner.lifecycleScope.coroutineContext)

    init {
        workerDetailsLiveData.observe(lifecycleOwner, ::observer)
    }

    private lateinit var workerDetails: WorkerDetails

    private fun observer(workerDetails: WorkerDetails) {
        Timber.tag("UpdateScheduler").d("observer: $workerDetails")
        this.workerDetails = workerDetails
    }

    fun startUpdates() {
        Timber.tag("UpdateScheduler").d("startUpdates")
        if (::workerDetails.isInitialized && workerDetails.workerRunning) {
            enqueueOneTimeWorker()
        } else {
            enqueuePeriodicWorker()
        }
    }

    private fun enqueuePeriodicWorker() {
        Timber.tag("UpdateScheduler").d("enqueuePeriodicWorker")
        val request =
            PeriodicWorkRequestBuilder<CurrencyWorker>(INTERVAL, TimeUnit.MINUTES)
                .addTag(PERIODIC_WORKER_TAG)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
        workManager.enqueueUniquePeriodicWork(
            PERIODIC_WORKER_TAG,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
        updateRunningState(true)
    }

    fun enqueueOneTimeWorker() {
        Timber.tag("UpdateScheduler").d("enqueueOneTimeWorker")
        val request = OneTimeWorkRequestBuilder<CurrencyWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        workManager.enqueue(request)
    }

    private fun updateRunningState(workerRunning: Boolean) {
        lifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            workerDetailsDataStore.updateData {
                it.copy(workerRunning = workerRunning)
            }
        }
    }

    fun stopUpdates() {
        Timber.tag("UpdateScheduler").d("stopUpdates")
        workManager.cancelAllWorkByTag(PERIODIC_WORKER_TAG)
        updateRunningState(false)
    }

    companion object {
        private const val PERIODIC_WORKER_TAG = "PERIODIC_UPDATER"
        private const val INTERVAL = 15L
    }
}
