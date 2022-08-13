package com.example.stopwatch

import android.content.*
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.roundToInt

class MainActivity2 : AppCompatActivity() {

    private lateinit var timeStopWatchService: TextView

    private lateinit var prefTextView: TextView
    private lateinit var prefEt: EditText
    private lateinit var prefBt: Button
    private var list = ArrayList<String>()

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        prefTextView = findViewById(R.id.prefString)
        prefEt = findViewById(R.id.prefET)
        prefBt = findViewById(R.id.prefBt)

        sharedPreferences = this.getSharedPreferences("LIST", Context.MODE_PRIVATE)
        editor =  sharedPreferences.edit()

        if (!sharedPreferences.getString("LIST", null).isNullOrEmpty()) {
            list = getList()
        }

        sharedPreferencesExample()

        timeStopWatchService = findViewById(R.id.timeStopWatchService)

        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }

    private fun sharedPreferencesExample() {
        prefBt.setOnClickListener {
            if (!prefEt.text.isNullOrEmpty()) {
                list.add(prefEt.text.toString())
                setLists(list)
                prefTextView.text = getList().toString()
            }
        }
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context, intent: Intent)
        {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            timeStopWatchService.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String
    {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02ds", hour, min, sec)

    //saving list in Shared Preference
    private fun setLists(list:ArrayList<String>){
        val gson = Gson()
        val json = gson.toJson(list)//converting list to Json
        editor.putString("LIST",json)
        editor.commit()
    }
    //getting the list from shared preference
    private fun getList():ArrayList<String>{
        val gson = Gson()
        val json = sharedPreferences.getString("LIST", null)
        val type = object : TypeToken<ArrayList<String>>(){}.type//converting the json to list
        return gson.fromJson(json,type)//returning the list
    }
}