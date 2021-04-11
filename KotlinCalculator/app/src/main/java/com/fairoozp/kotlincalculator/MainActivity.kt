package com.fairoozp.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var i  = "0"
    private var s  = "0"
    private var k = "0"
    private var op = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val n1 : Button = findViewById(R.id.n1)
        val n2 : Button = findViewById(R.id.n2)
        val n3 : Button = findViewById(R.id.n3)
        val n4 : Button = findViewById(R.id.n4)
        val n5 : Button = findViewById(R.id.n5)
        val n6 : Button = findViewById(R.id.n6)
        val n7 : Button = findViewById(R.id.n7)
        val n8 : Button = findViewById(R.id.n8)
        val n9 : Button = findViewById(R.id.n9)
        val n0 : Button = findViewById(R.id.n0)
        val dot : Button = findViewById(R.id.dot)
        val plus : Button = findViewById(R.id.plus)
        val minus : Button = findViewById(R.id.minus)
        val mul : Button = findViewById(R.id.mul)
        val div : Button = findViewById(R.id.div)
        val sum : Button = findViewById(R.id.sum)
        val clear : Button = findViewById(R.id.clear)
        val del : Button = findViewById(R.id.del)

        val input : TextView = findViewById(R.id.input)
        val result : TextView = findViewById(R.id.result)

        n1.setOnClickListener {
            input.append("1")
        }
        n2.setOnClickListener {
            input.append("2")
        }
        n3.setOnClickListener {
            input.append("3")
        }
        n4.setOnClickListener {
            input.append("4")
        }
        n5.setOnClickListener {
            input.append("5")
        }
        n6.setOnClickListener {
            input.append("6")
        }
        n7.setOnClickListener {
            input.append("7")
        }
        n8.setOnClickListener {
            input.append("8")
        }
        n9.setOnClickListener {
            input.append("9")
        }
        n0.setOnClickListener {
            input.append("0")
        }
        dot.setOnClickListener {
            input.append(".")
        }
        plus.setOnClickListener {
            input.append("+")
        }
        minus.setOnClickListener {
            input.append("-")
        }
        mul.setOnClickListener {
            input.append("*")
        }
        div.setOnClickListener {
            input.append("/")
        }
        clear.setOnClickListener {
            input.text = ""
            result.text = ""
        }
        del.setOnClickListener {
            var l : String = input.text.toString()
            l = l.dropLast(1)
            input.text = l
        }
        sum.setOnClickListener {
            i = input.text.toString()
            calculate()
            result.text = s
            op = 0
            k = "0"
        }
    }

    private fun calculate() {
        for (a in i.indices){
            when (val t : String = i[a].toString()) {
                "+" -> {
                    operate()
                    op = 1
                    k = "0"
                }
                "-" -> {
                    operate()
                    op = 2
                    k = "0"
                }
                "*" -> {
                    operate()
                    op = 3
                    k = "0"
                }
                "/" -> {
                    operate()
                    op = 4
                    k = "0"
                }
                else -> {
                    k += t
                }
            }
        }
        operate()
    }

    private fun operate() {
        when(op){
            0 -> { s = k }
            1 -> { s = (s.toFloat() + k.toFloat()).toString() }
            2 -> { s = (s.toFloat() - k.toFloat()).toString() }
            3 -> { s = (s.toFloat() * k.toFloat()).toString() }
            4 -> { s = (s.toFloat() / k.toFloat()).toString() }
        }
    }
}