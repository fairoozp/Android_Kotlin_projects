package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class MyWorkerProcessing(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    override fun doWork(): Result {
        return try {
            for (i in 1..10) {
                Log.d("MyLog", "Processing $i")
                Thread.sleep(1000)
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}