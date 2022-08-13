package com.example.audioplayer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var seekBar: SeekBar
    private lateinit var mediaPlayer: MediaPlayer
    private var handler = Handler(Looper.getMainLooper())
    private var playingPosition: Float? = null
    private var isPrepared = false

    private lateinit var plaButton: Button
    private lateinit var stopButton: Button
    private lateinit var timeStopWatch: TextView
    private var second = 0
    private var timeHandler = Handler(Looper.getMainLooper())
    private val timeRunnable: Runnable = Runnable { updateTimer() }
    private var isRunning = false

    private lateinit var plaButtonService: Button
    private lateinit var stopButtonService: Button
    private lateinit var timeStopWatchService: TextView

    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
        seekBar = findViewById(R.id.seekBar)
        mediaPlayer = MediaPlayer()

        plaButton = findViewById(R.id.playButton)
        stopButton = findViewById(R.id.stopButton)
        timeStopWatch = findViewById(R.id.timeStopWatch)

        plaButtonService = findViewById(R.id.playButtonService)
        stopButtonService = findViewById(R.id.stopButtonService)
        timeStopWatchService = findViewById(R.id.timeStopWatchService)

        setServiceStopWatch()

        setStopWatch()

        setMediaPlayer()
        setBufferingListener()

        seekBar.isEnabled = false

        progressBar.max = 100

        button.setOnClickListener {
            if (isPrepared) {
                if (mediaPlayer.isPlaying) {
                    handler.removeCallbacks(runnable)
                    mediaPlayer.pause()
                    //seekBar.isEnabled = false
                    button.text = "Play"
                } else {
                    mediaPlayer.start()
                    updateSeekBar()
                    seekBar.isEnabled = true
                    button.text = "Pause"
                }
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (isPrepared) {
                    //p0?.isEnabled = true
                    if (p2) {
                        if (mediaPlayer.isPlaying) {
                            handler.removeCallbacks(runnable)
                            mediaPlayer.pause()
                            playingPosition = ((mediaPlayer.duration.toFloat()) / 100) * p1
                            Log.e("progress", playingPosition.toString())
                            mediaPlayer.seekTo(playingPosition!!.toInt())
                            mediaPlayer.start()
                            updateSeekBar()
                        } else {
                            playingPosition = ((mediaPlayer.duration.toFloat()) / 100) * p1
                            Log.e("progress", playingPosition.toString())
                            mediaPlayer.seekTo(playingPosition!!.toInt())
                        }
                    }
                } else {
                    //p0?.isEnabled = false
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
//                handler.removeCallbacks(runnable)
//                mediaPlayer.pause()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //playingPosition = ((mediaPlayer.duration.toFloat()) / 100) * p0!!.progress
            }

        })
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

//        object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//                updateGUI(intent!!)
//            }
//        }
    }
    private fun updateGUI(intent: Intent) {
        if (intent.extras != null) {
            val hours = intent.getStringExtra("hours")
            val minutes = intent.getStringExtra("minutes")
            val seconds = intent.getStringExtra("seconds")
            val milliseconds = intent.getStringExtra("milliseconds")
            timeStopWatchService.text = "$hours:$minutes:$seconds.$milliseconds"
        }
    }


    private fun setStopWatch() {

        plaButton.setOnClickListener {
            if (isRunning) {
                isRunning = true
            } else {
                isRunning = true
                updateTimer()
            }
        }

        stopButton.setOnClickListener {
            isRunning = false
            second = 0
            handler.removeCallbacks(timeRunnable)
        }
    }

    private fun updateTimer() {
        if (isRunning) {
            val hr = second / 3600
            val min = (second % 3600) / 60
            val sec = second % 60
            val time = String.format(Locale.getDefault(), "%02d:%02d:%02ds", hr, min, sec)
            timeStopWatch.text = time
            second++
            timeHandler.postDelayed(timeRunnable, 1000)
        }
    }

    private fun setBufferingListener() {
        progressBar.secondaryProgressTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
        seekBar.secondaryProgressTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
        mediaPlayer.setOnBufferingUpdateListener { _, p1 ->
            progressBar.secondaryProgress = p1
            seekBar.secondaryProgress = p1
        }
    }

    private fun setMediaPlayer() {
        mediaPlayer.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
        mediaPlayer.prepare()
        preparedListener()
    }

    private fun preparedListener() {
        mediaPlayer.setOnPreparedListener { isPrepared = true }
    }

    private fun updateSeekBar() {
        if (mediaPlayer.isPlaying) {
            progressBar.progress =
                ((mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat()) * 100).toInt()
            seekBar.progress =
                ((mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat()) * 100).toInt()
            handler.postDelayed(runnable, 1000)
        }
    }

    private val runnable: Runnable = Runnable { updateSeekBar() }

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
        timerStarted = true
    }

    private fun stopTimer()
    {
        stopService(serviceIntent)
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

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02d", hour, min, sec)
}