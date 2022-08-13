package com.example.sqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitedatabase.adpter.RecyclerAdapter
import com.example.sqlitedatabase.databinding.ActivityMainBinding
import com.example.sqlitedatabase.db.Database
import com.example.sqlitedatabase.model.Person

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ArrayList<Person>()

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddNewUser::class.java))
        }

        val dataBase = Database(this)
        val data = dataBase.read()
        if (data.count>0) {
            while (data.moveToNext()) {
                val id = data.getString(0)
                val name = data.getString(1)
                val phoneNumber = data.getString(2)
                val userData = Person(id, name, phoneNumber)
                database.add(userData)
            }
        }

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = RecyclerAdapter(this,database)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.deleteMenuButton -> {
                val dataBase = Database(this)
                dataBase.deleteAll()
                Toast.makeText(applicationContext, "Table deleted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}