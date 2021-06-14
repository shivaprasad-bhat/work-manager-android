package com.svbneelmane.learn.notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.svbneelmane.learn.notify.databinding.ActivityMainBinding
import com.svbneelmane.learn.notify.services.NotificationSender
import com.svbneelmane.learn.notify.services.NotifyWorker
import com.svbneelmane.learn.notify.utils.Constants.Companion.CHANNEL_DESCRIPTION
import com.svbneelmane.learn.notify.utils.Constants.Companion.CHANNEL_ID
import com.svbneelmane.learn.notify.utils.Constants.Companion.CHANNEL_NAME
import timber.log.Timber
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Timber.plant(Timber.DebugTree())
        createNotificationChanel()
        createWorkRequest()

        with(binding) {
            actionButton.setOnClickListener {
                showNotification()
            }
        }
    }

    private fun showNotification() {
        val service = NotificationSender()
        service.sendNotification(this)
    }

    private fun createNotificationChanel() {
        Timber.i(":Constructing a notification channel..")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = CHANNEL_DESCRIPTION
            }

            val notificationManager: NotificationManager? = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

            notificationManager?.createNotificationChannel(channel)

        }
    }

    private fun createWorkRequest() {
        val worker = PeriodicWorkRequest.Builder(NotifyWorker::class.java, 16, TimeUnit.MINUTES)
                .build()

        WorkManager.getInstance(applicationContext).enqueue(worker)
    }
}