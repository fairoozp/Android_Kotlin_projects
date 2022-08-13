package com.example.spinner

data class DataClass(val language: String)

fun getDataClass() : ArrayList<DataClass>{
    val languages = ArrayList<DataClass>()
    languages.apply {
        this.add(DataClass("English"))
        this.add(DataClass("Malayalam"))
        this.add(DataClass("Hindi"))
        this.add(DataClass("TestingTestingTestingTestingTestingTestingTestingTesting"))
        this.add(DataClass("English"))
        this.add(DataClass("TestingTestingTestingTestingTestingTestingTestingTesting"))
        this.add(DataClass("English"))
        this.add(DataClass("TestingTestingTestingTestingTestingTestingTestingTesting"))
        this.add(DataClass("English"))
        this.add(DataClass("TestingTestingTestingTestingTestingTestingTestingTesting"))
        this.add(DataClass("English"))
        this.add(DataClass("TestingTestingTestingTestingTestingTestingTestingTesting"))
    }
    return languages
}