package com.example.searchcountrynames.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchcountrynames.R
import com.example.searchcountrynames.adapter.CountryAdapter
import com.example.searchcountrynames.model.CountryModel
import com.example.searchcountrynames.viewModel.CountryViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    private val _data = ArrayList<CountryModel>()
    private val data = ArrayList<CountryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel : CountryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        // only observe on events
        /* viewModel.country.observe(this, {
            data.addAll(it)
            searchData.addAll(it)
        }) */

        // to observe forever
        viewModel.country.observeForever { _data.addAll(it) }

        data.addAll(_data)

        recyclerView = findViewById(R.id.recyclerView)

        //set adapter and layout manager.
        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = CountryAdapter(this@MainActivity, data)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val item = menu?.findItem(R.id.searchButton)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextSubmit(query: String?): Boolean {
                data.clear()
                val searchText = query!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    _data.forEach {
                        if (it.name.lowercase(Locale.getDefault()).contains(searchText)){
                            data.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                else{
                    data.clear()
                    data.addAll(_data)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {

                data.clear()
                val searchText = newText!!.lowercase()
                if (searchText.isNotEmpty()){
                    _data.forEach {
                        if (it.name.lowercase().contains(searchText)){
                            data.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                else{
                    data.clear()
                    data.addAll(_data)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false

            }


        })

        return super.onCreateOptionsMenu(menu)
    }
}