package com.sebasdev.boldweatherapp.core_data.repository

import com.sebasdev.boldweatherapp.core_data.data_source.remote.WeatherRemoteDataSource
import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getSearchResultsByQuery(query: String): Resource<List<SearchLocationModel>> {
        return weatherRemoteDataSource.getSearchResultsByQuery(query)
    }

    override suspend fun getForecastByLocation(
        query: String,
        daysOfForecast: String
    ): Resource<ForecastModel> {
        return weatherRemoteDataSource.getForecastsOfLocation(query, daysOfForecast)
    }

}