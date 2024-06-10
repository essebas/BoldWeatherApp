package com.sebasdev.boldweatherapp.core_data.mappers

import com.sebasdev.boldweatherapp.core_data.dtos.ForecastDayDto
import com.sebasdev.boldweatherapp.core_data.dtos.ForecastDto
import com.sebasdev.boldweatherapp.core_data.dtos.LocationDto
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastDayModel
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastLocationModel
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel

fun LocationDto.toSearchLocationModelMap(): SearchLocationModel = SearchLocationModel(
    id = id,
    lat = lat,
    lon = lon,
    name = name,
    region = region,
    country = country,
    url = url,
)

fun List<LocationDto>.toListOfSearchLocationModelMap(): List<SearchLocationModel> =
    map { locationDto ->
        locationDto.toSearchLocationModelMap()
    }

fun LocationDto.toForecastModelMap(): ForecastLocationModel = ForecastLocationModel(
    timeZoneName = timeZoneName,
    localDateTimeUnixTime = localDateTimeUnixTime,
    localTime = localTime,
    lat = lat,
    lon = lon,
    name = name,
    region = region,
    country = country,
)

fun ForecastDayDto.toForecastDayModelMap(): ForecastDayModel = ForecastDayModel(
    date = date
)

fun List<ForecastDayDto>.toListOfForecastModel(): List<ForecastDayModel> =
    map { forecastDayDto ->
        forecastDayDto.toForecastDayModelMap()
    }

fun ForecastDto.toForecastModelMap(): ForecastModel = ForecastModel(
    location = location.toForecastModelMap(),
    current = "",
    forecast = forecast.listOfForecastDays.toListOfForecastModel()
)
