package com.sebasdev.boldweatherapp.forecast.domain.use_cases

import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import com.sebasdev.boldweatherapp.core_domain.util.ErrorCodes
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetForecastsOfLocationUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(
        query: String,
        daysOfForecast: String,
        language: String?
    ): Flow<Resource<ForecastModel>> = flow {
        try {
            emit(Resource.Loading())
            val forecastsOfLocation =
                weatherRepository.getForecastByLocation(query, daysOfForecast, language)
            emit(Resource.Success(forecastsOfLocation))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", e.code()))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Couldn't reach server. Check your internet connection.",
                    ErrorCodes.NO_INTERNET_CONNECTION
                )
            )
        }
    }
}