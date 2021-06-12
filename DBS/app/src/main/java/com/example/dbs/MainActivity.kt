package com.example.dbs

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")

    private val tdd : EditText = findViewById(R.id.tdd)
    private val postFood : EditText = findViewById(R.id.postFood)
    private val foodCarb : EditText = findViewById(R.id.foodCarb)
    private val isf : TextView = findViewById(R.id.isf)
    private val icr : TextView = findViewById(R.id.icr)
    private val cd : TextView = findViewById(R.id.cd)
    private val sv : Button = findViewById(R.id.sv)
    private val calc : Button = findViewById(R.id.calc)
    private val databaseHelper = DatabaseHelper(this)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tdd1 = 0f
        var postFood1 = 0f
        var foodCarb1 = 0f
        var isf1 = 0f
        var icr1 = 0f
        var cd1 = 0f

        var t = 1
        val change : TextView = findViewById(R.id.change)
        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.fbs->{
                    change.text = "FBS"
                    t=1
                }
                R.id.rbs->{
                    change.text = "RBS"
                    t=2
                }

            }
            true
        }

        calc.setOnClickListener {
            if (tdd.text.isNotEmpty() && postFood.text.isNotEmpty() && foodCarb.text.isNotEmpty()){
                tdd1 = tdd.text.toString().toFloat()
                postFood1 = postFood.text.toString().toFloat()
                foodCarb1 = foodCarb.text.toString().toFloat()
                isf1 = 1700/tdd1
                icr1 = 500/tdd1
                if (t==1){
                    cd1 = ((postFood1-130)/isf1)+(foodCarb1/icr1)
                    cd.text = cd1.toString()
                }
                if (t==2){
                    cd1 = ((postFood1-180)/isf1)+(foodCarb1/icr1)
                    cd.text = cd1.toString()
                }

                isf.text = isf1.toString()
                icr.text = icr1.toString()
            }
            else{
                Toast.makeText(this, "Enter all Field", Toast.LENGTH_SHORT).show()
            }
        }
        sv.setOnClickListener {
            val date = Calendar.getInstance().time
            val result : Boolean = databaseHelper.insertData(date.toString(),tdd1.toString(),postFood1.toString(),foodCarb1.toString(),isf1.toString(),icr1.toString(),cd1.toString())
            when{
                result -> Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
            clearAll()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun clearAll() {
        tdd.text.clear()
        postFood.text.clear()
        foodCarb.text.clear()
        isf.text = "0"
        icr.text = "0"
        cd.text = "0"
    }
}