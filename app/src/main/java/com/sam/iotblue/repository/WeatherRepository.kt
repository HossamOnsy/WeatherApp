package com.sam.iotblue.repository


import com.sam.iotblue.model.WeatherResponseModel
import com.sam.iotblue.network.RestApi
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class WeatherRepository(var restApi: RestApi) {


    fun getCurrentWeather(latitude: String, longitude: String): Observable<WeatherResponseModel> {
        return restApi.getCurrentWeather(latitude = latitude, longitude = longitude)
            .debounce(400, TimeUnit.MILLISECONDS)
            .map {
                it
            }
            .onErrorReturn { null }
    }


}
