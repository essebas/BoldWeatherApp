package com.sebasdev.boldweatherapp.core_data.data_source.remote

import com.sebasdev.boldweatherapp.core_data.data_source.remote.api.WeatherApi
import com.sebasdev.boldweatherapp.core_data.mappers.toListOfLocationModelMap
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.search.domain.models.LocationModel
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRemoteDataSource {
    override suspend fun getSearchResultsByQuery(query: String): Resource<List<LocationModel>> {
        return try {
            Resource.Success(
                data = weatherApi.getSearchResultsByQuery(
                    apiKey = "de5553176da64306b86153651221606",
                    searchQuery = query
                ).toListOfLocationModelMap()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown ocurred.")
        }
    }

}