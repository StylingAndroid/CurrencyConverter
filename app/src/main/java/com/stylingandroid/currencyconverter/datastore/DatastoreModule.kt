package com.stylingandroid.currencyconverter.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    fun providesWorkerDetailsDataStore(
        @ApplicationContext context: Context
    ): DataStore<WorkerDetails> =
        context.createDataStore(
            fileName = "WorkerDetails.pb",
            serializer = WorkerDetailsSerializer
        )
}
