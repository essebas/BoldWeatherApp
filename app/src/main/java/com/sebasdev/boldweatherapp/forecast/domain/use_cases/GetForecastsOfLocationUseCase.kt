package com.sebasdev.boldweatherapp.forecast.domain.use_cases

import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import javax.inject.Inject

class GetForecastsOfLocationUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(query: String, daysOfForecast: String): Resource<ForecastModel> {
        return weatherRepository.getForecastByLocation(query, daysOfForecast)
    }
}