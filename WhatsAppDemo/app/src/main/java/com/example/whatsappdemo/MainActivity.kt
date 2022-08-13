package com.example.whatsappdemo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#008069")))
            isHideOnContentScrollEnabled
            elevation = 0f

        }

        val viewPager : ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = PageAdapter(supportFragmentManager,lifecycle)

        val tabLayout : TabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout,viewPager){ tab , position ->
            when (position){
                0 -> {
                    tab.text = "CHATS"
                }
                1 ->{
                   tab.text =  "STATUS"
                }
                2 -> {
                    tab.text =  "CALLS"
                }
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                Toast.makeText(applicationContext, "click on Search Button", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}