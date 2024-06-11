package com.sebasdev.boldweatherapp.core_data.data_source.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebasdev.boldweatherapp.core_data.data_source.local.db.entities.SearchedLocationEntity

@Dao
interface SearchedLocationDao {

    @Query("SELECT * FROM SearchedLocationEntity")
    suspend fun getAll(): List<SearchedLocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(searchedLocationEntity: SearchedLocationEntity)

}