package com.sebasdev.boldweatherapp.core_data.data_source.remote.api

import com.sebasdev.boldweatherapp.core_data.dtos.ForecastDto
import com.sebasdev.boldweatherapp.core_data.dtos.LocationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("search.json")
    suspend fun getSearchResultsByQuery(
        @Query("key") apiKey: String,
        @Query("q") searchQuery: String
    ): List<LocationDto>

    @GET("forecast.json")
    suspend fun getForecastOfLocation(
        @Query("key") apiKey: String,
        @Query("q") searchQuery: String,
        @Query("days") daysOfForecast: String,
    ): ForecastDto

}