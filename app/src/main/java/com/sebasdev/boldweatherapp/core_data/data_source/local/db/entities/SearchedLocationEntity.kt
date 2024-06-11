package com.sebasdev.boldweatherapp.core_data.data_source.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchedLocationEntity(
    @PrimaryKey val idLocation: Int,
    @ColumnInfo(name = "location_name") val locationName: String,
    @ColumnInfo(name = "location_country") val locationCountry: String
)