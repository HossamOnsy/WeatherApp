package com.sam.iotblue.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sam.iotblue.R

class TodaysWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todays_weather)


        if (intent!=null && intent.hasExtra("lat")&& intent.hasExtra("lon")){

        }
    }
}
