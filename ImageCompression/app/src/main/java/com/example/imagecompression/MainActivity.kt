package com.example.imagecompression

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.imagecompression.databinding.ActivityMainBinding
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.default
import id.zelory.compressor.constraint.destination
import id.zelory.compressor.constraint.quality
import kotlinx.coroutines.*
import java.io.File
import java.util.*
import android.provider.MediaStore
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        const val TITLE = "Please select an image"
        const val PROGRESS = "Compressing your image....."
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var originalImage: File
    private lateinit var compressedImage: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleText.visibility = View.INVISIBLE
        binding.titleText.text = TITLE
        binding.progressBar.visibility = View.INVISIBLE
        binding.progressText.visibility = View.INVISIBLE

        binding.addFromGalleryFab.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            resultLauncher.launch(intent)
        }
    }

    private fun processImage() {
        GlobalScope.launch {

            runOnUiThread(Runnable {
                displayOriginalImage()
            })

            var time = Calendar.getInstance().time
            Log.e("Testing", "Image Compressing Starting $time")
            compressImage()
            time = Calendar.getInstance().time
            Log.e("Testing", "Image Compressing Finishing $time")

            runOnUiThread(Runnable {
                displayCompressedImage()
            })
        }
    }

    private fun displayCompressedImage() {
        binding.titleText.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        binding.progressText.visibility = View.INVISIBLE
        val myBitmapCompressed = BitmapFactory.decodeFile(compressedImage.path)
        binding.compressed.setImageBitmap(myBitmapCompressed)
    }

    private fun displayOriginalImage() {
        binding.titleText.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.progressText.visibility = View.VISIBLE
        binding.progressText.text = PROGRESS
        val myBitmapCompressed = BitmapFactory.decodeFile(originalImage.path)
        binding.original.setImageBitmap(myBitmapCompressed)
    }

    private suspend fun compressImage() {
        withContext(Dispatchers.IO) {
            Compressor.compress(this@MainActivity, originalImage){
                default(format = Bitmap.CompressFormat.PNG)
                //quality(80)
                destination(compressedImage)
                //size(2_097_152)
            }
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            //doSomeOperations()

            val selectedImage = data?.data
            val filePath = arrayOf(MediaStore.Images.Media.DATA)
            val c: Cursor? = contentResolver.query(selectedImage!!, filePath, null, null, null)
            c!!.moveToFirst()
            val columnIndex: Int = c.getColumnIndex(filePath[0])
            val picturePath: String = c.getString(columnIndex)
            c.close()

            originalImage = File(picturePath)
            compressedImage = File("${originalImage.path}.compressed.png")

            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(originalImage.path, options)
            if (options.outWidth != -1 && options.outHeight != -1) {
                // This is an image file.
                processImage()
            } else {
                // This is not an image file.
                Toast.makeText(this, "Please select an image file", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
