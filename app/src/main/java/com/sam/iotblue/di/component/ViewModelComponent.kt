package com.sam.iotblue.di.component

import com.sam.iotblue.di.modules.NetworkModule
import com.sam.iotblue.di.modules.ReposModule
import com.sam.iotblue.ui.activities.MainActivity
import com.sam.iotblue.ui.activities.WeatherViewModel
import dagger.Component


@Component(modules = [(NetworkModule::class),(ReposModule::class)])
interface ViewModelComponent {

    fun inject(weatherViewModel: WeatherViewModel)

}