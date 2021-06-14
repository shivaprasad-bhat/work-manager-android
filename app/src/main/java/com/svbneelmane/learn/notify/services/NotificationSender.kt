package com.svbneelmane.learn.notify.services

import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.svbneelmane.learn.notify.R
import com.svbneelmane.learn.notify.utils.Constants.Companion.CHANNEL_ID
import com.svbneelmane.learn.notify.utils.Constants.Companion.NOTIFICATION_CONTENT_TEXT
import com.svbneelmane.learn.notify.utils.Constants.Companion.NOTIFICATION_ID
import com.svbneelmane.learn.notify.utils.Constants.Companion.NOTIFICATION_TITLE

class NotificationSender() {

    fun sendNotification(context: Context) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentTitle(NOTIFICATION_TITLE)
            .setStyle(NotificationCompat.BigTextStyle().bigText(NOTIFICATION_CONTENT_TEXT))
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, builder)
        }
    }
}