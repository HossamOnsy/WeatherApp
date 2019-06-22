package com.sam.iotblue.di.modules


import com.sam.iotblue.network.RestApi
import com.sam.iotblue.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object ReposModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideWeatherRepo(restApi: RestApi): WeatherRepository {
        return WeatherRepository(restApi)
    }
}