package com.sam.iotblue.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sam.iotblue.R
import com.sam.iotblue.ui.viewmodels.WeatherViewModel
import kotlinx.android.synthetic.main.activity_todays_weather.*

class TodaysWeatherActivity : AppCompatActivity() {

    lateinit var viewModel: WeatherViewModel
    lateinit var toast: Toast

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todays_weather)
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        if (intent != null && intent.hasExtra("lat") && intent.hasExtra("lon")) {
            viewModel.getCurrentWeather(intent.getStringExtra("lat"), intent.getStringExtra("lon"))
            toast = Toast.makeText(this, "Error Occured , please try again later ...", Toast.LENGTH_SHORT)
            setObservers()
        }
    }


    private fun setObservers() {
        loadingObserve()
        successObserve()
        errorObserve()
    }


    private fun loadingObserve() {
        viewModel.loadingVisibility.observe(this, Observer {
            progress_bar.visibility = it

        })

    }

    private fun errorObserve() {
        viewModel.errorMessage.observe(this, Observer {
            toast.cancel()
            if (it != null) {
                toast = Toast.makeText(this, it, Toast.LENGTH_SHORT)
                toast.show()
            }
        })

    }

    private fun successObserve() {
        viewModel.success.observe(this, Observer {
            toast.cancel()
            if (it != null) {
                tv_weather_value.text = "${it.weather?.get(0)?.main}"
                tv_weather_desc_value.text = "${it.weather?.get(0)?.description}"
                tv_humidity_value.text = "${it.main?.humidity}"
                tv_wind_speed_value.text = "${it.wind?.speed}"
                tv_wind_degree_value.text = "${it.wind?.deg}"


                if (it.rain?.h != null)
                    tv_rain_percent_value.text="${it.rain.h}"
                else
                    tv_rain_percent.visibility= View.GONE


                if (it.main?.temp != null)
                    tv_min_max_degree_value.text = "${it.main.temp}"
                else
                    tv_min_max_degree.visibility= View.GONE


            }
        })

    }
}
