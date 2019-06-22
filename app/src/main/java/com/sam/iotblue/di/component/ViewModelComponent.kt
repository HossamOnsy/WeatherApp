package com.sam.iotblue.di.component

import com.sam.iotblue.di.modules.NetworkModule
import com.sam.iotblue.ui.activities.MainActivity
import dagger.Component


@Component(modules = [(NetworkModule::class)])
interface ViewModelComponent {

    fun inject(mainActivity: MainActivity)

}