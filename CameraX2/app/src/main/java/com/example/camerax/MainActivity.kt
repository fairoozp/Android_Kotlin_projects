package com.example.camerax

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.OrientationEventListener
import android.view.Surface
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.camerax.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cameraController: LifecycleCameraController
    var resultFile: File?=null
    private val orientationEventListener by lazy {
        object : OrientationEventListener(this) {
            override fun onOrientationChanged(orientation: Int) {
                if (orientation == ORIENTATION_UNKNOWN) {
                    return
                }
                val rotation = when (orientation) {
                    in 45 until 135 -> Surface.ROTATION_270
                    in 135 until 225 -> Surface.ROTATION_180
                    in 225 until 315 -> Surface.ROTATION_90
                    else -> Surface.ROTATION_0
                }
                binding.previewView.rotation = rotation.toFloat()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCamera()
        setListeners()
    }

    private fun setCamera() {
        cameraController = LifecycleCameraController(this)
        cameraController.bindToLifecycle(this)
        binding.previewView.controller = cameraController
        cameraController.isPinchToZoomEnabled = true
        cameraController.isTapToFocusEnabled = true
        cameraController.imageCaptureFlashMode = ImageCapture.FLASH_MODE_OFF
        cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    }

    private fun setListeners() {
        binding.btnCamera.setOnClickListener {
            if (isCameraPermissionGranted(this@MainActivity)) {
                takePicFromCamera()
            } else {
                requestCameraPermissions.launch(
                    arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                )
            }
        }

        binding.capture.btnCancel.setOnClickListener {
            binding.clVisibility.visibility = View.VISIBLE
            binding.capture.clResult.visibility = View.GONE
        }

        binding.capture.btnUpload.setOnClickListener {
            val intent= Intent()
            intent.putExtra("path",resultFile.toString())
            setResult(5, intent)
            finish()
        }
        binding.btnCameraSwitch.setOnClickListener {
            when(cameraController.cameraSelector) {
                CameraSelector.DEFAULT_BACK_CAMERA -> {
                    cameraController.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                }
                CameraSelector.DEFAULT_FRONT_CAMERA -> {
                    cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                }
            }
        }
        binding.btnCameraFlash.setOnClickListener {
            when (cameraController.imageCaptureFlashMode) {
                ImageCapture.FLASH_MODE_OFF -> {
                    cameraController.imageCaptureFlashMode = ImageCapture.FLASH_MODE_ON
                    binding.btnCameraFlash.setImageResource(R.drawable.camera_flash_on_icon)
                }
                ImageCapture.FLASH_MODE_ON -> {
                    cameraController.imageCaptureFlashMode = ImageCapture.FLASH_MODE_AUTO
                    binding.btnCameraFlash.setImageResource(R.drawable.camera_flash_auto_icon)
                }
                ImageCapture.FLASH_MODE_AUTO -> {
                    cameraController.imageCaptureFlashMode = ImageCapture.FLASH_MODE_OFF
                    binding.btnCameraFlash.setImageResource(R.drawable.camera_flash_off__icon)
                }
            }
        }
    }

    private fun getExecutor(): Executor {
        return ContextCompat.getMainExecutor(this)
    }

    override fun onStart() {
        super.onStart()
        orientationEventListener.enable()
    }

    override fun onStop() {
        super.onStop()
        orientationEventListener.disable()
    }

    /**
     * Request camera, external storage permissions
     */
    private val requestCameraPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (checkPermissionGranted(permissions.entries)) {
                takePicFromCamera()
            }
        }

    private fun takePicFromCamera() {
        binding.btnCamera.isEnabled = false
        binding.btnCamera.alpha = 0.5f
        resultFile = getTmpFileUri(this)
        if(resultFile!=null) {
            cameraController.takePicture(
                ImageCapture.OutputFileOptions.Builder(resultFile!!).build(),
                getExecutor(),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        binding.btnCamera.isEnabled = true
                        binding.btnCamera.alpha = 1f
                        binding.clVisibility.visibility = View.GONE
                        binding.capture.clResult.visibility = View.VISIBLE
                        binding.capture.ivImage.setImageURI(Uri.fromFile(resultFile))
                    }

                    override fun onError(exception: ImageCaptureException) {
                        binding.btnCamera.isEnabled = true
                        binding.btnCamera.alpha = 1f
                        Toast.makeText(
                            this@MainActivity,
                            "Error saving photos" + exception.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }

    fun getTmpFileUri(context: Context): File {
        val folder = File("${context.getExternalFilesDir(Environment.DIRECTORY_DCIM)}")
        folder.mkdirs()
        val file = File(folder, getCurrentTime() + ".jpg")
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()
        return file
    }

    fun getCurrentTime(): String {
        return SimpleDateFormat(TimeExtensions.CURRENT_TIME_FORMAT.ext, Locale.getDefault()).format(
            Date()
        )
    }

    fun checkPermissionGranted(permissionResults: MutableSet<MutableMap.MutableEntry<String, Boolean>>): Boolean {
        var cameraResult = false
        var externalStorage = false
        if (permissionResults.isNotEmpty()) {
            for (permission in permissionResults) {
                if (permission.key == Manifest.permission.CAMERA && permission.value) {
                    cameraResult = true
                }
                if (permission.key == Manifest.permission.READ_EXTERNAL_STORAGE && permission.value) {
                    externalStorage = true
                }
            }
        }
        if (cameraResult && externalStorage) {
            return true
        }
        return false
    }

    fun isCameraPermissionGranted(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true
            }
        }
        return false
    }
}

enum class TimeExtensions(val ext: String) {
    HOUR_MIN("HH:mm"),
    CURRENT_TIME_FORMAT("yyyyMMdd_HHmmss"),
    UTC_FORMAT("UTC"),
    TIME_FORMAT("h:mm a"),
    STOP_WATCH_FORMAT("%02d:%02d:%02ds")
}