package com.sebasdev.boldweatherapp.core_data.repository

import com.sebasdev.boldweatherapp.core_data.data_source.remote.WeatherRemoteDataSource
import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.search.domain.models.LocationModel
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getSearchResultsByQuery(query: String): Resource<List<LocationModel>> {
        return weatherRemoteDataSource.getSearchResultsByQuery(query)
    }

}