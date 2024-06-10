package com.sebasdev.boldweatherapp.core_data.mappers

import com.sebasdev.boldweatherapp.core_data.dtos.LocationDto
import com.sebasdev.boldweatherapp.search.domain.models.LocationModel

fun LocationDto.toLocationModelMap(): LocationModel = LocationModel(
    id = id,
    lat = lat,
    lon = lon,
    name = name,
    region = region,
    country = country,
    url = url,
)

fun List<LocationDto>.toListOfLocationModelMap(): List<LocationModel> = map { locationDto ->
    locationDto.toLocationModelMap()
}
