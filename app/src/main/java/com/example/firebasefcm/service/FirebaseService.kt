package com.example.firebasefcm.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.resources.Compatibility.Api18Impl.setAutoCancel
import androidx.core.app.NotificationCompat
import com.example.firebasefcm.MainActivity
import com.example.firebasefcm.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class FirebaseService : FirebaseMessagingService() {

    val channelId = "firebase_channel"

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,FLAG_ONE_SHOT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = Random().nextInt()

        if (Build. VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannelOlustur(notificationManager)
        }


        val notification = NotificationCompat.Builder( this, channelId)
        .setContentTitle (message.data ["baslik"])
            .setContentText (message.data ["mesaj"])
            .setSmallIcon (R.drawable.ic_baseline_album_24)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()


        notificationManager.notify(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notificationChannelOlustur(notificationManager: NotificationManager) {
        val channelName = "firebaseChannel"
        val channel = NotificationChannel (channelId, channelName, IMPORTANCE_HIGH).apply {
            description = "Kanal tanımı"
            enableLights (true)
            lightColor = Color.MAGENTA
        }
        notificationManager.createNotificationChannel(channel)
    }

}