package com.example.retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.retrofit.api.DataModelApi
import com.example.retrofit.model.MyDataItem
import com.example.retrofit.model.MyDataModel
import com.example.retrofit.databinding.ActivityMain2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar2.visibility = View.GONE
        binding.buttonSave.setOnClickListener {
            if (binding.editTextTextName.text.isNotEmpty() && binding.editTextTextJob.text.isNotEmpty()) {
                saveData()
            } 
            else {
                Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show()
            }
            
        }

    }

    private fun saveData() {
        binding.progressBar2.visibility = View.VISIBLE
        val body = binding.editTextTextName.text.toString()
        val title = binding.editTextTextJob.text.toString()
        //val uid = binding.uid.toString().toInt()
        //val sid = binding.sid.toString().toInt()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataModelApi::class.java)

        val modal = MyDataItem(body, 121, title, 124)

        val call = retrofit.createPost(modal)

        //val retrofitAPI: DataModelApi = retrofit.create(DataModelApi::class.java)
        //val call: Call<MyDataModel> = retrofitAPI.createPost(modal)

        call.enqueue(object : Callback<MyDataModel?> {
            override fun onResponse(call: Call<MyDataModel?>, response: Response<MyDataModel?>) {
                Toast.makeText(this@MainActivity2, "Data added to api", Toast.LENGTH_SHORT).show()
                binding.progressBar2.visibility = View.GONE
                binding.editTextTextName.text.clear()
                binding.editTextTextJob.text.clear()
                val responseFromAPI = response.body()
                val responseString = """
                    Response Code : ${response.code()}
                    Name : 
                    Job : 
                    """.trimIndent()
                binding.textViewResponse.text = responseString
            }

            override fun onFailure(call: Call<MyDataModel?>, t: Throwable) {
                binding.textViewResponse.text = "Error found is : " + t.message
                binding.progressBar2.visibility = View.GONE
            }
        })

        binding.textViewResponse.text = ""
    }
}