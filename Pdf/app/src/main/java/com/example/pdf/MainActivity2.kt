package com.example.pdf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

import es.voghdev.pdfviewpager.library.util.FileUtil

import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter

import es.voghdev.pdfviewpager.library.RemotePDFViewPager

import android.view.View

import android.widget.ProgressBar

import es.voghdev.pdfviewpager.library.remote.DownloadFile
import java.lang.Exception


class MainActivity2 : AppCompatActivity(), DownloadFile.Listener{

    private var remotePDFViewPager: RemotePDFViewPager? = null
    private var pdfPagerAdapter: PDFPagerAdapter? = null
    private var url: String? = null
    private var progressBar: ProgressBar? = null
    private var pdfLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //set the Visibility of the progressbar to visible
        progressBar = findViewById(R.id.progressBar)
        progressBar!!.visibility = View.VISIBLE

        //initialize the pdfLayout
        pdfLayout = findViewById(R.id.pdf_layout)

        //initialize the url variable
        url = getString(R.string.url)

        //Create a RemotePDFViewPager object
        remotePDFViewPager = RemotePDFViewPager(this, url, this)
    }



    override fun onSuccess(url: String, destinationPath: String) {

        // That's the positive case. PDF Download went fine
        pdfPagerAdapter = PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url))
        remotePDFViewPager!!.adapter = pdfPagerAdapter
        updateLayout()
        progressBar!!.visibility = View.GONE
    }

    private fun updateLayout() {
        pdfLayout!!.addView(
            remotePDFViewPager,
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
    }

    override fun onFailure(e: Exception) {
        // This will be called if download fails
    }

    override fun onProgressUpdate(progress: Int, total: Int) {
        // You will get download progress here
        // Always on UI Thread so feel free to update your views here
    }

    public override fun onDestroy() {
        super.onDestroy()
        if (pdfPagerAdapter != null) {
            pdfPagerAdapter!!.close()
        }
    }
}