package com.example.stopwatch

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.animation.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private lateinit var plaButtonService: Button
    private lateinit var stopButtonService: Button
    private lateinit var timeStopWatchService: TextView

    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    private lateinit var gif: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plaButtonService = findViewById(R.id.playButtonService)
        stopButtonService = findViewById(R.id.stopButtonService)
        timeStopWatchService = findViewById(R.id.timeStopWatchService)

        gif = findViewById(R.id.gif)
        setGif()
        setAnim()

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        setServiceStopWatch()
    }

    private fun setAnim() {
        val anim: ImageView = findViewById(R.id.anim)

        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator() //add this
        fadeIn.duration = 1000

        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator() //and this
        fadeOut.startOffset = 1000
        fadeOut.duration = 1000

        val animation = AnimationSet(false) //change to false
        animation.addAnimation(fadeIn)
        animation.addAnimation(fadeOut)
        animation.repeatCount = Animation.INFINITE
        animation.duration = 2000

        val animApply = RotateAnimation(0f, 350f, 15f, 15f)
        animApply.interpolator = LinearInterpolator()
        animApply.repeatCount = Animation.INFINITE
        animApply.duration = 700

        anim.animation = animation
    }

    private fun setGif() {
        Glide
            .with(this)
            .asGif()
            .load(R.raw.watter)
            .into(gif)
    }

    private fun setServiceStopWatch() {
        plaButtonService.setOnClickListener {
            startStopTimer()
        }
        stopButtonService.setOnClickListener {
            resetTimer()
        }

        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }

    private fun resetTimer()
    {
        stopTimer()
        time = 0.0
        timeStopWatchService.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer()
    {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer()
    {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        plaButtonService.text = "Stop"
        timerStarted = true
    }

    private fun stopTimer()
    {
        stopService(serviceIntent)
        plaButtonService.text = "Start"
        timerStarted = false
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
}