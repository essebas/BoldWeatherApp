package com.sebasdev.boldweatherapp.core_data.data_source.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sebasdev.boldweatherapp.core_data.data_source.local.db.daos.SearchedLocationDao
import com.sebasdev.boldweatherapp.core_data.data_source.local.db.entities.SearchedLocationEntity

@Database(entities = [SearchedLocationEntity::class], version = 1)
abstract class WeatherDataBase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "weather-database"
    }

    abstract fun searchedLocationDao(): SearchedLocationDao
}