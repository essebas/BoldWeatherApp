package com.sebasdev.boldweatherapp.core_data.data_source.remote

import com.sebasdev.boldweatherapp.core_data.data_source.remote.api.WeatherApi
import com.sebasdev.boldweatherapp.core_data.dtos.ForecastDto
import com.sebasdev.boldweatherapp.core_data.mappers.toForecastModelMap
import com.sebasdev.boldweatherapp.core_data.mappers.toListOfSearchLocationModelMap
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRemoteDataSource {
    override suspend fun getSearchResultsByQuery(query: String): Resource<List<SearchLocationModel>> {
        return try {
            Resource.Success(
                data = weatherApi.getSearchResultsByQuery(
                    apiKey = "de5553176da64306b86153651221606",
                    searchQuery = query
                ).toListOfSearchLocationModelMap()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown occurred.")
        }
    }

    override suspend fun getForecastsOfLocation(
        query: String,
        daysOfForecast: String
    ): Resource<ForecastModel> {
        return try {
            Resource.Success(
                data = weatherApi.getForecastOfLocation(
                    apiKey = "de5553176da64306b86153651221606",
                    searchQuery = query,
                    daysOfForecast = daysOfForecast
                ).toForecastModelMap()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown occurred.")
        }
    }

}