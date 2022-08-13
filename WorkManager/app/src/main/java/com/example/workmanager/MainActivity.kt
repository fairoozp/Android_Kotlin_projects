package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.work.*
import com.example.workmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            setOneTimeRequest()
        }
        binding.startButtonConstraint.setOnClickListener {
            setOneTimeRequestConstraint()
        }
        binding.startButtonData.setOnClickListener {
            setOneTimeRequestData()
        }
        binding.buttonChainedSeq.setOnClickListener {
            setChainedRequestSequential()
        }
        binding.buttonChainedPar.setOnClickListener {
            setChainedRequestParallel()
        }
        binding.buttonPeriodic.setOnClickListener {
            setPeriodicRequest()
        }

    }

    private fun setOneTimeRequest() {
        val workManager = WorkManager.getInstance(this)
        val uploadRequest = OneTimeWorkRequest.Builder(MyWorkManager::class.java)
            .build()
        workManager.enqueue(uploadRequest)
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this) {
                binding.result.text = it.state.name
            }
    }

    private fun setOneTimeRequestConstraint() {
        val workManager = WorkManager.getInstance(this)
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val uploadRequest = OneTimeWorkRequest.Builder(MyWorkManager::class.java)
            .setConstraints(constraints)
            .build()
        workManager.enqueue(uploadRequest)
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this) {
                binding.resultConstraint.text = it.state.name
            }
    }

    private fun setOneTimeRequestData() {
        val workManager = WorkManager.getInstance(this)
        val data: Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 15)
            .build()
        val uploadRequest = OneTimeWorkRequest.Builder(MyWorkManagerData::class.java)
            .setInputData(data)
            .build()
        workManager.enqueue(uploadRequest)
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this) {
                binding.resultData.text = it.state.name
                if (it.state.isFinished) {
                    val dataDate = it.outputData
                    val date = dataDate.getString(MyWorkManagerData.KEY_WORKER)
                    Toast.makeText(this, date, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun setChainedRequestSequential() {
        val workManager = WorkManager.getInstance(this)
        val uploadRequest = OneTimeWorkRequest.Builder(MyWorkManager::class.java)
            .build()
        val processingRequest = OneTimeWorkRequest.Builder(MyWorkerProcessing::class.java)
            .build()
        val downloadRequest = OneTimeWorkRequest.Builder(MyWorkerDownloading::class.java)
            .build()
        workManager
            .beginWith(uploadRequest)
            .then(processingRequest)
            .then(downloadRequest)
            .enqueue()
        workManager.getWorkInfoByIdLiveData(downloadRequest.id)
            .observe(this) {
                binding.chainedResultSeq.text = it.state.name
            }
    }

    private fun setChainedRequestParallel() {
        val workManager = WorkManager.getInstance(this)
        val uploadRequest = OneTimeWorkRequest.Builder(MyWorkManager::class.java)
            .build()
        val processingRequest = OneTimeWorkRequest.Builder(MyWorkerProcessing::class.java)
            .build()
        val downloadRequest = OneTimeWorkRequest.Builder(MyWorkerDownloading::class.java)
            .build()
        val parallelWorks: MutableList<OneTimeWorkRequest> = mutableListOf()
        parallelWorks.apply {
            add(processingRequest)
            add(uploadRequest)
        }
        workManager
            .beginWith(parallelWorks)
            .then(downloadRequest)
            .enqueue()
        workManager.getWorkInfoByIdLiveData(downloadRequest.id)
            .observe(this) {
                binding.chainedResultPar.text = it.state.name
            }
    }

    private fun setPeriodicRequest() {
        val workManager = WorkManager.getInstance(this)
        val periodicWorkRequest = PeriodicWorkRequest
            .Builder(MyWorkerDownloading::class.java, 15, TimeUnit.MINUTES)
            .build()
        workManager.enqueue(periodicWorkRequest)
        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id)
            .observe(this) {
                binding.resultPeriodic.text = it.state.name
            }
    }

    companion object {
        const val KEY_COUNT_VALUE = "Key_Count"
    }
}