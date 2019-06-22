package com.sam.iotblue.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sam.iotblue.model.Coord


// Made is as a Util Function so later on it would used whenever an Exchanging Rate is required
// Of course we can change currencyRateMapping -> fetch it from Room or the last saved CurrencyConversionList

object AppUtils {

    fun saveCoordsToList(coord: Coord, context: Context) {
        var coordsList: ArrayList<Coord>? = getListOfCoords(context)
        if (coordsList == null) coordsList = ArrayList()
        coordsList.add(coord)
        saveListOfCoords(coordsList, context)
    }


    fun saveListOfCoords(coordsList: ArrayList<Coord>, context: Context) {
        val shref: SharedPreferences
        val editor: SharedPreferences.Editor
        shref = PreferenceManager.getDefaultSharedPreferences(context)

        val gson = Gson()
        val json = gson.toJson(coordsList)

        editor = shref.edit()
        editor.remove(KEY_COORDS_LIST).apply()
        editor.putString(KEY_COORDS_LIST, json)
        editor.commit()
    }

    fun getListOfCoords(context: Context): ArrayList<Coord> {
        val shref = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val response = shref.getString(KEY_COORDS_LIST, "[]")

        return gson.fromJson(
            response,
            object : TypeToken<ArrayList<Coord>>(){}.type
        )
    }

}