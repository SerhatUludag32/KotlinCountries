package com.serhatuludag.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhatuludag.kotlincountries.model.Country

class FeedViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country("Turkey","Ankara","Asia","TRY","Turkish","www.xy.com")
        val country2 = Country("France","Paris","Europe","EUR","French","www.xy.com")
        val country3 = Country("Germany","Berlin","Europe","EUR","German","www.xy.com")

        val countryList = arrayListOf<Country>(country,country2,country3)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false

    }
}