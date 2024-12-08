package com.serhatuludag.kotlincountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.serhatuludag.kotlincountries.model.Country

@Dao
interface CountryDAO {
    // Data Access Object interface

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>
    // suspend -> coroutine scope pause&resume
    // vararg -> multiple country objects

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

    @Query("DELETE FROM country WHERE uuid = :countryId")
    suspend fun deleteCountry(countryId : Int)
}