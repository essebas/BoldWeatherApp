package com.sebasdev.boldweatherapp.core_data.dtos

import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("maxtemp_c")
    val maxTempC: Double,
    @SerializedName("maxtemp_f")
    val maxTempF: Double,
    @SerializedName("mintemp_c")
    val minTempC: Double,
    @SerializedName("mintemp_f")
    val minTempF: Double,
    @SerializedName("avgtemp_c")
    val avgTempC: Double,
    @SerializedName("avgtemp_f")
    val avgTempF: Double,
    @SerializedName("maxwind_mph")
    val maxWindMPH: Double,
    @SerializedName("maxwind_kph")
    val maxWindKPH: Double,
    @SerializedName("totalprecip_mm")
    val totalPrecipMM: Double,
    @SerializedName("totalprecip_in")
    val totalPrecipIN: Double,
    @SerializedName("totalsnow_cm")
    val totalSnowCM: Double,
    @SerializedName("avgvis_km")
    val avgvisKM: Double,
    @SerializedName("avgvis_miles")
    val avgvisMiles: Double,
    @SerializedName("avghumidity")
    val avgHumidity: Int,
    @SerializedName("daily_will_it_rain")
    val dailyWillItRain: Int,
    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int,
    @SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Int,
    @SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int,
    val condition: ConditionDto,
    val uv: Double
)
