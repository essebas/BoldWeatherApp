package com.sebasdev.boldweatherapp.search.domain.use_cases

import com.sebasdev.boldweatherapp.core_domain.repository.WeatherRepository
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import javax.inject.Inject

class SaveConsultedLocationUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(searchLocationModel: SearchLocationModel) {
        searchLocationModel.id?.let { idLocation ->
            weatherRepository.saveConsultedLocation(
                idLocation,
                searchLocationModel.name,
                searchLocationModel.country
            )
        }
    }
}