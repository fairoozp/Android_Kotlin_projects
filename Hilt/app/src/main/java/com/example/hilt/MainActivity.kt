package com.example.hilt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var string: String
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.textView)
        val textViewVm: TextView = findViewById(R.id.textViewVM)
        val ediText: EditText = findViewById(R.id.editText)
        val editTextValue: TextView = findViewById(R.id.editTextValue)
        val button: Button = findViewById(R.id.button)
        val sharedVm: Button = findViewById(R.id.shared_vm)
        val sharedVmAc: Button = findViewById(R.id.shared_vm_ac)
        val navDrawer: Button = findViewById(R.id.nav_drawer)

        textView.text = string

        textViewVm.text = viewModel.string

        button.setOnClickListener {
            val name = viewModel.getText(ediText.text.toString())
            editTextValue.text = name
        }

        sharedVm.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        navDrawer.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }

        sharedVmAc.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
        }

    }
}