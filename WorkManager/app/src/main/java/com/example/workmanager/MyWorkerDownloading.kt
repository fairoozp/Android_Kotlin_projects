package com.example.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MyWorkerDownloading(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        return try {
            for (i in 1..10) {
                Log.d("MyLog", "Downloading $i")
                Thread.sleep(1000)
            }
            val time = SimpleDateFormat("dd/mm/yy hh:mm:ss")
            val currentDate = time.format(Date())
            Log.d("MyLog", "Downloading Completed $currentDate")
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}