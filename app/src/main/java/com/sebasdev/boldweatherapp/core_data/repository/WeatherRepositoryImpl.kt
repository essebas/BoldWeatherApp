package com.sebasdev.boldweatherapp.core_data.repository

import com.sebasdev.boldweatherapp.core_data.data_source.local.WeatherLocalDataSource
import com.sebasdev.boldweatherapp.core_data.data_source.remote.WeatherRemoteDataSource
import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource
) : WeatherRepository {

    override suspend fun getSearchResultsByQuery(query: String): List<SearchLocationModel> {
        return weatherRemoteDataSource.getSearchResultsByQuery(query)
    }

    override suspend fun getForecastByLocation(
        query: String,
        daysOfForecast: String,
        language: String?
    ): ForecastModel {
        val languageToRequest = language ?: Locale.getDefault().language
        return weatherRemoteDataSource.getForecastsOfLocation(
            query,
            daysOfForecast,
            languageToRequest
        )
    }

    override suspend fun saveConsultedLocation(
        idLocation: Int,
        nameLocation: String,
        countryLocation: String
    ) {
        weatherLocalDataSource.insertSearchedLocation(idLocation, nameLocation, countryLocation)
    }

    override suspend fun getSearchLocationsSaved(): List<SearchLocationModel> {
        return weatherLocalDataSource.getSearchLocationsSaved()
    }

}