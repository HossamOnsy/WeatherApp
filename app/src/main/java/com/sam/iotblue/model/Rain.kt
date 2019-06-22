package com.sam.iotblue.model


import com.squareup.moshi.Json

data class Rain(
    @Json(name = "3h")
    val h: Double?
)