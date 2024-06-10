package com.sebasdev.boldweatherapp.core_data.dtos

import com.google.gson.annotations.SerializedName

data class HourDto(
    @SerializedName("time_epoch")
    val timeEpoch: Int,
    @SerializedName("time")
    val time: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("is_day")
    val isDay: Int,
    val condition: ConditionDto,
    @SerializedName("wind_mph")
    val windMPH: Double,
    @SerializedName("wind_kph")
    val windKPH: Double,
    @SerializedName("wind_degree")
    val windDegree: Int,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure_mb")
    val pressureMB: Double,
    @SerializedName("pressure_in")
    val pressureIN: Double,
    @SerializedName("precip_mm")
    val precipMM: Double,
    @SerializedName("precip_in")
    val precipIN: Double,
    @SerializedName("snow_cm")
    val snowCM: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("cloud")
    val cloud: Int,
    @SerializedName("feelslike_c")
    val feelsLikeC: Double,
    @SerializedName("feelslike_f")
    val feelsLikeF: Double,
    @SerializedName("windchill_c")
    val windChilC: Double,
    @SerializedName("windchill_f")
    val windChillF: Double,
    @SerializedName("heatindex_c")
    val heatIndexC: Double,
    @SerializedName("heatindex_f")
    val heatIndexF: Double,
    @SerializedName("dewpoint_c")
    val dewpointC: Double,
    @SerializedName("dewpoint_f")
    val dewpointF: Double,
    @SerializedName("will_it_rain")
    val willItRain: Int,
    @SerializedName("chance_of_rain")
    val chanceOfRain: Int,
    @SerializedName("will_it_snow")
    val willItSnow: Int,
    @SerializedName("chance_of_snow")
    val chanceOfSnow: Int,
    @SerializedName("vis_km")
    val visKM: Double,
    @SerializedName("vis_miles")
    val visMiles: Double,
    @SerializedName("gust_mph")
    val gustMPH: Double,
    @SerializedName("gust_kph")
    val gustKPH: Double,
    @SerializedName("uv")
    val uv: Double,
)
