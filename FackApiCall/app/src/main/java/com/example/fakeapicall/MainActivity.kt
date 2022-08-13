package com.example.fakeapicall

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.github.gcacace.signaturepad.views.SignaturePad
import android.os.Environment
import android.widget.EditText
import android.widget.Toast
import java.io.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.TextView
import androidx.core.util.Pair
import com.google.android.material.datepicker.MaterialDatePicker
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signaturePad: SignaturePad = findViewById(R.id.signature_pad)
        val clearBt: Button = findViewById(R.id.clear_bt)
        val saveBt: Button = findViewById(R.id.save_bt)
        val date: Button = findViewById(R.id.button)
        val downloadFolder: Button = findViewById(R.id.button2)
        saveBt.isClickable = false
        clearBt.isClickable = false

        val textView: TextView = findViewById(R.id.textView)

        val et1 : EditText = findViewById(R.id.et_1)
        val et2: EditText = findViewById(R.id.et_2)

        downloadFolder.setOnClickListener {
            val path1 =
                File(getExternalFilesDir(null).toString() + "/" + "Download" + "/")
            val path =
                Environment.getExternalStorageDirectory().toString() + "/" + "Downloads" + "/"
            val uri: Uri = Uri.parse(path)
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(uri, "*/*")
            //startActivity(intent)
            textView.text = path
        }

        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .setSelection(Pair(
                    MaterialDatePicker.thisMonthInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()
                ))
                .build()

        date.setOnClickListener {
            dateRangePicker.show(supportFragmentManager, "tag")
            dateRangePicker.addOnPositiveButtonClickListener {
                val start = convertLongToDate(it.first)
                val end = convertLongToDate(it.second)
                textView.text = "start date $start and the end date $end"
            }
        }

        et1.setOnTouchListener(OnTouchListener { v, event ->
            if (v.id == R.id.et_1) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        })

        et2.setOnTouchListener(OnTouchListener { v, event ->
            if (v.id == R.id.et_2) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        })

        signaturePad.setOnSignedListener(object : SignaturePad.OnSignedListener {
            override fun onStartSigning() {
                saveBt.isClickable = false
                clearBt.isClickable = false
            }

            override fun onSigned() {
                saveBt.isClickable = true
                clearBt.isClickable = true
            }

            override fun onClear() {
                saveBt.isClickable = false
                clearBt.isClickable = false
            }
        })

        clearBt.setOnClickListener {
            Toast.makeText(this, "Signature cleared successfully", Toast.LENGTH_SHORT).show()
            signaturePad.clear()
        }

        saveBt.setOnClickListener {
            val sign = signaturePad.signatureBitmap
            val file = getTmpFileUri(this)
            val result = bitmapToFilePng(sign, file.path)
            if (result!!.isFile) {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                signaturePad.clear()
            }
            else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertLongToDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTmpFileUri(context: Context): File {
        val folder = File("${context.getExternalFilesDir(Environment.DIRECTORY_DCIM)}")
        folder.mkdirs()
        val sdf = SimpleDateFormat("yyyyMMdd_hhmmss")
        val currentDate = sdf.format(Date())
        val file = File(folder, "IMG_$currentDate.png")
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()
        return file
    }

    private fun bitmapToFilePng(bitmap: Bitmap, fileNameToSave: String): File? { // File name like "image.png"
        var file: File? = null
        return try {
            file = File(fileNameToSave)
            file.createNewFile()

            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitmapData = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }
}