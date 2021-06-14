package com.svbneelmane.learn.notify.services

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber
import java.lang.Exception

class NotifyWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val context = context

    override fun doWork(): Result {
        return try {
            val service = NotificationSender()
            service.sendNotification(context)
            Timber.d("Success")
            Result.success()
        } catch (e: Exception) {
            Timber.e("Error")
            Result.failure()
        }
    }
}