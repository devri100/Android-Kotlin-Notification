package com.demo.notification

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val service = context?.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("mychannel", "My Channel", NotificationManager.IMPORTANCE_HIGH)
            service.createNotificationChannel(channel)
        }

        val newIntent = Intent(context, MainActivity::class.java)
        newIntent.putExtra("fragment", "1")
        //newIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(context, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, "mychannel")
            .setSmallIcon(R.drawable.ic_android)
            .setContentTitle("Értesítés")
            .setContentText("Ez egy értesítés")
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle().bigText("Ez egy értesítés Ez egy értesítés Ez egy értesítés Ez egy értesítés Ez egy értesítés Ez egy értesítés Ez egy értesítés "))
            .setAutoCancel(true)
            .build()

        service.notify(0, notification)
    }
}