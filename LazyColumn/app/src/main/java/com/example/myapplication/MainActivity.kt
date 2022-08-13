package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val  sections = listOf(1,11,21,31,41)

            LazyColumn {
                sections.forEach {section ->
                    stickyHeader {
                        Text(
                            modifier = Modifier
                                .padding(12.dp)
                                .height(24.dp)
                                .background(Color.LightGray)
                                .fillMaxWidth(),
                            text = "Number from $section to ${section+9}")
                    }
                    items(10){
                        Text(modifier = Modifier.padding(12.dp),
                            text = "Number ${section+it}")
                    }
                }
                item {
                    Text(
                        modifier = Modifier
                            .padding(12.dp)
                            .height(24.dp)
                            .background(Color.LightGray)
                            .fillMaxWidth(),
                        text = "Finished")
                }
            }


        }
    }
}

