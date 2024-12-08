package com.serhatuludag.kotlincountries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.serhatuludag.kotlincountries.model.Country

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDAO

    // Singleton

    companion object {

        @Volatile
        private var instance: CountryDatabase? = null
        // Volatile -> For make accessible to other threads

        // Control instance is available or not, if not create one.
        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: makeDatabase(context).also {
                instance = it
            }

        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java,
            "countrydatabase"
        ).build()

    }
}

