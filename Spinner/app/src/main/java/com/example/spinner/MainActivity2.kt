package com.example.spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iosprogressbarforandroid.IOSProgressHUD
import com.example.spinner.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pbMaterial.setOnClickListener {
            IOSProgressHUD.create(this)
                .setStyle(IOSProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        }
    }
}