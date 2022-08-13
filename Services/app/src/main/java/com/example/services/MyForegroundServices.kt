package com.example.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi

class MyForegroundServices : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()

        Log.e("Service", "Service is Starting")

        Thread {
            kotlin.run {
                while (true) {
                    Log.e("Service", "Service is Running...")
                    Thread.sleep(2000)
                }
            }
        }.start()
        val id = "Foreground service ID"
        val channel = NotificationChannel(id, id, NotificationManager.IMPORTANCE_LOW)
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, id)
            .setContentTitle("ForeGround Service")
            .setContentText("Service Running...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
        startForeground(1001, notification.build())

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "service Killed", Toast.LENGTH_SHORT).show()
        Log.e("Service", "Service is Killed")
    }
}