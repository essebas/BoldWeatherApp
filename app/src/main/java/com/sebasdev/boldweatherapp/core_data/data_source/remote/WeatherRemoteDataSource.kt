package com.sebasdev.boldweatherapp.core_data.data_source.remote

import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.search.domain.models.LocationModel

interface WeatherRemoteDataSource {
    suspend fun getSearchResultsByQuery(query: String): Resource<List<LocationModel>>
}