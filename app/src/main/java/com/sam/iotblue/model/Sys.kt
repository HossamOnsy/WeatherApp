package com.sam.iotblue.model


import com.squareup.moshi.Json

data class Sys(
    @Json(name = "country")
    val country: String?,
    @Json(name = "message")
    val message: Double?,
    @Json(name = "sunrise")
    val sunrise: Int?,
    @Json(name = "sunset")
    val sunset: Int?
)