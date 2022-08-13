package com.example.services

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.services.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()
    private lateinit var foregroundStatus: String
    private lateinit var backgroundStatus: String

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

        binding.startServicesBackground.setOnClickListener {
            Intent(this, MyBackgroundServices::class.java).also { intent ->
                startService(intent)
            }
            viewModel.statusBackgroundServices = RUNNING
            loadData()
            binding.serviceStatusBackground.text = backgroundStatus
        }

        binding.stopServicesBackground.setOnClickListener {
            Intent(this, MyBackgroundServices::class.java).also { intent ->
                stopService(intent)
            }
            viewModel.statusBackgroundServices = STOPPED
            loadData()
            binding.serviceStatusBackground.text = backgroundStatus
        }

        binding.startServicesForeground.setOnClickListener {
            Intent(this, MyForegroundServices::class.java).also { intent ->
                startForegroundService(intent)
            }
            viewModel.statusForegroundServices = RUNNING
            loadData()
            binding.serviceStatusForeground.text = foregroundStatus
        }

        binding.stopServicesForeground.setOnClickListener {
            Intent(this, MyForegroundServices::class.java).also { intent ->
                stopService(intent)
            }
            viewModel.statusForegroundServices = STOPPED
            loadData()
            binding.serviceStatusForeground.text = foregroundStatus
        }

    }

    private fun loadData() {
        viewModel.statusBackgroundServicesLiveData.value = viewModel.statusBackgroundServices
        viewModel.statusForegroundServicesLiveData.value = viewModel.statusForegroundServices

        viewModel.statusForegroundServicesLiveData.observeForever {
            foregroundStatus = it
        }
        viewModel.statusBackgroundServicesLiveData.observeForever {
            backgroundStatus = it
        }
        binding.serviceStatusBackground.text = backgroundStatus
        binding.serviceStatusForeground.text = foregroundStatus
    }

    companion object {
        const val RUNNING = "Service Running..."
        const val STOPPED = "Service Stopped"
    }
}