package com.sebasdev.boldweatherapp.core_data.mappers

import com.sebasdev.boldweatherapp.core_data.data_source.local.db.entities.SearchedLocationEntity
import com.sebasdev.boldweatherapp.core_data.dtos.ConditionDto
import com.sebasdev.boldweatherapp.core_data.dtos.CurrentDto
import com.sebasdev.boldweatherapp.core_data.dtos.ForecastDayDto
import com.sebasdev.boldweatherapp.core_data.dtos.ForecastDto
import com.sebasdev.boldweatherapp.core_data.dtos.LocationDto
import com.sebasdev.boldweatherapp.forecast.domain.models.ConditionModel
import com.sebasdev.boldweatherapp.forecast.domain.models.CurrentModel
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

@JvmName("MapperListOfSearchLocationModel")
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
    date = date,
    conditionModel = this.day.condition.toConditionModelMap(),
    maxTemperatureInCelsius = this.day.maxTempC
)

fun List<ForecastDayDto>.toListOfForecastModel(): List<ForecastDayModel> =
    map { forecastDayDto ->
        forecastDayDto.toForecastDayModelMap()
    }

fun ConditionDto.toConditionModelMap(): ConditionModel = ConditionModel(
    text, "https:${icon}", code
)

fun SearchedLocationEntity.toSearchLocationModelMap(): SearchLocationModel = SearchLocationModel(
    id = idLocation,
    name = locationName,
    country = locationCountry,
    lat = 0.0,
    lon = 0.0,
    region = "",
    url = ""
)

fun List<SearchedLocationEntity>.toListOfSearchLocationModelMap(): List<SearchLocationModel> =
    map { searchedLocationEntity ->
        searchedLocationEntity.toSearchLocationModelMap()
    }

fun CurrentDto.toCurrentModelMap(): CurrentModel = CurrentModel(
    lastUpdated = lastUpdated,
    tempInCelsius = tempC,
    isDay = isDay,
    condition = condition.toConditionModelMap(),
    windSpeedInKilometers = windKph,
    humidity = humidity,
    heatIndexInCelsius = heatIndexC
)

fun ForecastDto.toForecastModelMap(): ForecastModel = ForecastModel(
    location = location.toForecastModelMap(),
    current = current.toCurrentModelMap(),
    forecast = forecast.listOfForecastDays.toListOfForecastModel()
)
