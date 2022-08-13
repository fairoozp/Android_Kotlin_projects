package com.example.services

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast

class MyBackgroundServices : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

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

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "service Killed", Toast.LENGTH_SHORT).show()
        Log.e("Service", "Service is Killed")
    }
}