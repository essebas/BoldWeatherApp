package com.sebasdev.boldweatherapp.search.domain.use_cases

import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import com.sebasdev.boldweatherapp.core_domain.util.ErrorCodes
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSearchLocationsSavedUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(): Flow<Resource<List<SearchLocationModel>>> = flow {
        try {
            emit(Resource.Loading())
            val searchLocationsSaved = weatherRepository.getSearchLocationsSaved()
            emit(Resource.Success(searchLocationsSaved))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage, ErrorCodes.EMPTY_RESULT))
        }
    }
}