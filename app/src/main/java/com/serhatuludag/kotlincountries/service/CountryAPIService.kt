package com.serhatuludag.kotlincountries.service

import com.serhatuludag.kotlincountries.model.Country
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getData() : Single<List<Country>> {
        return api.getCountries()
    }
}