package com.example.customspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spinner)
        val modelList: List<Model> = readFromAsset()
        val customDropDownAdapter = Adapter(this, modelList)
        spinner.adapter = customDropDownAdapter
    }

    private fun readFromAsset(): List<Model> {
        val modelList: List<Model> = listOf(
            Model("data1 cygiyaebyvciyuebgviycybegtyyfwu8acentv7w8y9eucnywrecvergvbergfe"),
            Model("data2 cygiyaebyvciyuebgviycybegbrtnymrtnrbegrtnmy,umntghtm,jg"),
            Model("data3 cygiyaebyvciyuebgviycybegrhtryju,efgrfhmgjhjhmfgndfsasdnfhmgjg"),
            Model("data4 cygiyaebyvciyuebgviycybefmgjkhjghfdsafghfjgfhkg"),
            Model("data5 cygiyaebyvciyuebgviythyjgfukhijghjfgdfcybeg"),
            Model("data6 cygiyaebyvciyuebgviycybjhkgjfhdgrsefafrgthfygeg"),
            Model("data7 cygiyaebyvciyuebgviycybeg")
        )
        return modelList
    }
}