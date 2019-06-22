package com.sam.iotblue.ui.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sam.iotblue.base.BaseViewModel
import com.sam.iotblue.di.component.DaggerViewModelComponent
import com.sam.iotblue.model.WeatherResponseModel
import com.sam.iotblue.repository.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel  : BaseViewModel(){




    @Inject
    lateinit var weatherRepository: WeatherRepository
    val success: MutableLiveData<WeatherResponseModel> = MutableLiveData()

    init {
        DaggerViewModelComponent.builder().build().inject(this)
    }

    fun getCurrentWeather(latitude: String,longitude:String) {

        subscription = weatherRepository.getCurrentWeather(latitude,longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(
                {

                    onRetrieveWeatherSuccess(it)

                },
                { error -> onRetrieveDataError(error) }
            )
    }


    private fun onRetrieveWeatherSuccess(it: WeatherResponseModel) {
        loadingVisibility.value = View.GONE
        success.value = it
    }
}
