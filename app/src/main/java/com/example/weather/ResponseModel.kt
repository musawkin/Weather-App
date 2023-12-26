package com.example.weather

data class ResponseModel(
    val location: Location,
    val current: Current,
    val forecast: Forecast

)

data class Location(
    val name: String?,

)

data class Current(
    val temp_c: Double?,
    val temp_f: Double?,
    val condition: Condition,
    val wind_kph: Double?,
    val pressure_mb: Double?,
    val humidity: Int?

)

data class Condition(
    val text: String?,
    val icon: String?,
    val code: Int?,
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String?,
    val day: Day,
    val hour: List<Hour>
)

data class Day(
    val maxtemp_c: Double?,
    val mintemp_c: Double?,
    val maxtemp_f: Double?,
    val mintemp_f: Double?,
    val maxwind_kph: Double?,
    val avghumidity: Double?,
    val condition: Condition
)

data class Hour(
    val time: String,
    val temp_c: Double?,
    val temp_f: Double?,
    val condition: Condition,
    val wind_kph: Double?,
    val pressure_mb: Double?,
    val humidity: Int?,

)