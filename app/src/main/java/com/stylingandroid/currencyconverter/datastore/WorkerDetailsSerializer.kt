package com.stylingandroid.currencyconverter.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object WorkerDetailsSerializer : Serializer<WorkerDetails> {

    override fun readFrom(input: InputStream): WorkerDetails {
        return try {
            WorkerDetails.ADAPTER.decode(input)
        } catch (exception: IOException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    override fun writeTo(t: WorkerDetails, output: OutputStream) {
        WorkerDetails.ADAPTER.encode(output, t)
    }

    override val defaultValue: WorkerDetails = WorkerDetails()
}
