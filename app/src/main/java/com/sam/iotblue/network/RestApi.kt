package com.sam.iotblue.network


import com.sam.iotblue.model.WeatherResponseModel
import com.sam.iotblue.utils.APP_ID
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("APPID") APPID: String = APP_ID,
        @Query("units") units: String = "metric"
    ): Observable<WeatherResponseModel>


}