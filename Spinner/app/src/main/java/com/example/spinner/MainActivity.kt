package com.example.spinner

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.spinner.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var languages: ArrayList<DataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        languages = getDataClass()
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown, languages)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView2.setOnClickListener {
            popup(binding.autoCompleteTextView2, binding.autoCompleteTextView2)
        }
        binding.autoCompleteTextView3.setOnClickListener {
            popup(binding.autoCompleteTextView3, binding.autoCompleteTextView3)
        }
        binding.imageView.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    private fun popup(view: View, textView: AutoCompleteTextView) {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.custom_dropdown, null)

        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        val popupWindow = PopupWindow(popupView, width, height, true)
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        popupWindow.showAsDropDown(view, 0, 10)

        val recyclerView = popupView.findViewById<RecyclerView>(R.id.recyclerView2)
        val adapter = Adapter(languages, listener = {
            textView.setText(languages[it].language)
            popupWindow.dismiss()
        })
        if (languages.size > 5) {
            val param = recyclerView.layoutParams
            param.height = binding.width.height * 5
            recyclerView.layoutParams = param
        }
        recyclerView.adapter = adapter

    }

}