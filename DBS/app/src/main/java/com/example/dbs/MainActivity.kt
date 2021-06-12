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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tdd : EditText = findViewById(R.id.tdd)
        val postFood : EditText = findViewById(R.id.postFood)
        val foodCarb : EditText = findViewById(R.id.foodCarb)
        val isf : TextView = findViewById(R.id.isf)
        val icr : TextView = findViewById(R.id.icr)
        val cd : TextView = findViewById(R.id.cd)
        val sv : Button = findViewById(R.id.sv)
        val calc : Button = findViewById(R.id.calc)

        var tdd1: Float
        var postFood1: Float
        var foodCarb1: Float
        var isf1: Float
        var icr1: Float
        var cd1: Float

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
            val id = Calendar.getInstance().time
            Toast.makeText(this, "Time is $id", Toast.LENGTH_SHORT).show()
            tdd.text.clear()
            postFood.text.clear()
            foodCarb.text.clear()
            isf.text = "0"
            icr.text = "0"
            cd.text = "0"
        }

    }
}