package com.sebasdev.boldweatherapp.search.domain.use_cases

import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.search.domain.models.LocationModel
import javax.inject.Inject

class SearchLocationsUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(query: String): Resource<List<LocationModel>> {
        return weatherRepository.getSearchResultsByQuery(query)
    }

}