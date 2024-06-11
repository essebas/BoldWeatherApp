package com.sebasdev.boldweatherapp.core_domain.repository

import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel

interface WeatherRepository {

    suspend fun getSearchResultsByQuery(query: String): List<SearchLocationModel>

    suspend fun getForecastByLocation(
        query: String,
        daysOfForecast: String,
        language: String?
    ): ForecastModel

    suspend fun saveConsultedLocation(
        idLocation: Int,
        nameLocation: String,
        countryLocation: String
    )

    suspend fun getSearchLocationsSaved(): List<SearchLocationModel>

}