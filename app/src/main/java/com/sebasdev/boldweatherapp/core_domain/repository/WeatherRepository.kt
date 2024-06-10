package com.sebasdev.boldweatherapp.core_domain.repository

import com.sebasdev.boldweatherapp.core_data.dtos.ForecastDto
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel

interface WeatherRepository {

    suspend fun getSearchResultsByQuery(query: String): Resource<List<SearchLocationModel>>

    suspend fun getForecastByLocation(
        query: String,
        daysOfForecast: String
    ): Resource<ForecastModel>

}