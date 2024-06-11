package com.sebasdev.boldweatherapp.search.domain.use_cases

import android.util.Log
import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import com.sebasdev.boldweatherapp.core_domain.util.ErrorCodes
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class SearchLocationsUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    operator fun invoke(query: String): Flow<Resource<List<SearchLocationModel>>> = flow {
        try {
            emit(Resource.Loading())

            when {
                query.isBlank() || (query.length < 3) -> emit(
                    Resource.Error(
                        "",
                        ErrorCodes.EMPTY_RESULT
                    )
                )

                else -> {
                    val locationsList = weatherRepository.getSearchResultsByQuery(query)
                    emit(Resource.Success(locationsList))
                }
            }

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