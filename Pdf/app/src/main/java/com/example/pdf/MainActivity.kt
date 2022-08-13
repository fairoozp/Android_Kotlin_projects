package com.example.pdf

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.net.toUri
import androidx.loader.content.AsyncTaskLoader
import com.github.barteksc.pdfviewer.PDFView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var inputStream: InputStream
    private lateinit var input: InputStream

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pdf: PDFView = findViewById(R.id.pdfView)
        //pdf.fromUri(URL.toUri()).load()
        pdf.fromAsset("pdf.pdf").load()
        //val inputStream = URL(URL).openStream()

        val floatingActionButton: FloatingActionButton = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

    }

    companion object {
        const val URL = "https://mhapisupport-testing.ycalabs.com/assets/public/policies/Privacy_Policy_A-m8_MobileApp.pdf"
    }
}