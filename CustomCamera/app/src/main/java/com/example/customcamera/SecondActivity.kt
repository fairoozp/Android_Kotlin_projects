package com.example.customcamera

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.core.Preview.OnPreviewOutputUpdateListener
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.customcamera.databinding.ActivitySecondBinding
import java.io.File


class SecondActivity : AppCompatActivity()
{
    private lateinit var binding:ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkRequestPermissions(this, 1)) {
                takeImageFromCamera()
            }
        } else {
            takeImageFromCamera()
        }
    }

    private fun takeImageFromCamera() {
        CameraX.unbindAll()
        val aspectRatio = Rational(binding.textureView.width, binding.textureView.height)
        val screen = Size(binding.textureView.width, binding.textureView.height)
        val pConfig = PreviewConfig.Builder().setTargetAspectRatio(aspectRatio).setTargetResolution(screen).build()
        val preview = Preview(pConfig)
        preview.onPreviewOutputUpdateListener =
            OnPreviewOutputUpdateListener { output ->

                //to update the surface texture we  have to destroy it first then re-add it
                val parent = binding.textureView.parent as ViewGroup
                parent.removeView(binding.textureView)
                parent.addView(binding.textureView, 0)
                binding.textureView.setSurfaceTexture(output.surfaceTexture)
                updateTransform()
            }
        val imageCaptureConfig =  ImageCaptureConfig.Builder().setCaptureMode(
            ImageCapture.CaptureMode.MIN_LATENCY)
            .setTargetRotation(windowManager.defaultDisplay.rotation).build();
         val  imgCap = ImageCapture(imageCaptureConfig);
        binding.imgCapture.setOnClickListener {
            val file = File(
                Environment.getExternalStorageDirectory()
                    .toString() + "/" + System.currentTimeMillis() + ".png"
            )
            imgCap.takePicture(file,object:ImageCapture.OnImageSavedListener{
                override fun onImageSaved(file: File) {
                    val msg = "Pic captured at " + file.absolutePath
                    Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
                }

                override fun onError(
                    useCaseError: ImageCapture.UseCaseError,
                    message: String,
                    cause: Throwable?
                ) {
                    val msg = "Pic capture failed : $message"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
                    cause?.printStackTrace()
                }

            })
            CameraX.bindToLifecycle(this as LifecycleOwner, preview, imgCap)
        }
    }

    private fun checkRequestPermissions(context: Context, requestIdMultiplePermissions: Int): Boolean {
        val storagePermission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val cameraPermission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
        val listPermissionsNeeded: MutableList<String> = java.util.ArrayList()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA)
        }
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(context as Activity, listPermissionsNeeded.toTypedArray(), requestIdMultiplePermissions)
            return false
        }
        return true
    }

    private fun updateTransform() {
        val mx = Matrix()
        val w: Int = binding.textureView.measuredWidth
        val h: Int = binding.textureView.measuredHeight
        val cX = w / 2f
        val cY = h / 2f
        val rotationDgr: Int
        val rotation = binding.textureView.rotation
        rotationDgr = when (rotation.toInt()) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        mx.postRotate(rotationDgr.toFloat(), cX, cY)
        binding.textureView.setTransform(mx)
    }
}