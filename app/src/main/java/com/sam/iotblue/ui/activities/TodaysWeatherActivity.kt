package com.sam.iotblue.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sam.iotblue.R
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
                if (it.main?.temp != null)
                    tv_min_max_degree_value.text = "${it.main.temp}"
                else
                    tv_min_max_degree.visibility= View.GONE


            }
        })

    }
}
