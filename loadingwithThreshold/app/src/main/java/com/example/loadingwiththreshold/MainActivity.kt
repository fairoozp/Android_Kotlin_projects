package com.example.loadingwiththreshold

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var page = 1
    private val limit = 20
    private val data = ArrayList<Data>()

    init {
        getPage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar : ProgressBar = findViewById(R.id.progressBar)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        progressBar.visibility = View.GONE
        val layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(data) {
            startActivity(Intent(this, MainActivity2::class.java))
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            @SuppressLint("NotifyDataSetChanged")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0){
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount
                    if ((visibleItemCount+pastVisibleItem) >= (total-5)){
                        page++
                        getPage()
                        progressBar.visibility = View.VISIBLE
                        Handler(Looper.getMainLooper()).postDelayed({
                            adapter.notifyDataSetChanged()
                            progressBar.visibility = View.GONE
                        },1000)
                        //adapter.notifyDataSetChanged()
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    private fun getPage(){
        val start = (page-1)*limit+1
        val end = page*limit
        for (i in (start)..end){
            data.add(Data("Item $i"))
        }
    }
}