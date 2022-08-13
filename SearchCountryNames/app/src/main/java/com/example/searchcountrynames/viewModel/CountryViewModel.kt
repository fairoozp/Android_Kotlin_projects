package com.example.searchcountrynames.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchcountrynames.model.CountryModel
import com.example.searchcountrynames.repository.CountryRepository

class CountryViewModel : ViewModel() {
    private val repo = CountryRepository()
    private val data = repo.country

    private val _country : MutableLiveData<ArrayList<CountryModel>> by lazy {
        MutableLiveData<ArrayList<CountryModel>>()
    }
    val country : LiveData<ArrayList<CountryModel>> get() = _country
    init {
        _country.value = data
    }
}