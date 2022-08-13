package com.example.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MyWorkManagerData(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        return try {
            val count = inputData.getInt(MainActivity.KEY_COUNT_VALUE, 0)
            for (i in 1..count) {
                Log.d("MyLog", "Uploading $i")
                Thread.sleep(1000)
            }
            val time = SimpleDateFormat("dd/mm/yy hh:mm:ss")
            val currentDate = time.format(Date())
            val outputData = Data.Builder()
                .putString(KEY_WORKER, currentDate)
                .build()
            Result.success(outputData)
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val KEY_WORKER = "Key_Worker"
    }
}