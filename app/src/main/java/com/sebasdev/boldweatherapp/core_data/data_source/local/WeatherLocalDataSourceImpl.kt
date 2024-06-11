package com.sebasdev.boldweatherapp.core_data.data_source.local

import com.sebasdev.boldweatherapp.core_data.data_source.local.db.WeatherDataBase
import com.sebasdev.boldweatherapp.core_data.data_source.local.db.entities.SearchedLocationEntity
import com.sebasdev.boldweatherapp.core_data.mappers.toListOfSearchLocationModelMap
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(
    private val weatherDataBase: WeatherDataBase
) : WeatherLocalDataSource {
    override suspend fun insertSearchedLocation(idLocation: Int, name: String, country: String) {
        val searchedDao = weatherDataBase.searchedLocationDao()
        searchedDao.insertLocation(
            SearchedLocationEntity(
                idLocation,
                name,
                country
            )
        )
    }

    override suspend fun getSearchLocationsSaved(): List<SearchLocationModel> {
        val searchedDao = weatherDataBase.searchedLocationDao()
        return searchedDao.getAll().toListOfSearchLocationModelMap()
    }
}