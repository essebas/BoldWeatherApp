package com.sebasdev.boldweatherapp.core_data.data_source.remote

import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel

interface WeatherRemoteDataSource {
    suspend fun getSearchResultsByQuery(query: String): List<SearchLocationModel>

    suspend fun getForecastsOfLocation(
        query: String,
        daysOfForecast: String,
        language: String
    ): ForecastModel
}