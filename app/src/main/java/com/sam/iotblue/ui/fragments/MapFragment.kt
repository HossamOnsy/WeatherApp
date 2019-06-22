package com.sam.iotblue.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sam.iotblue.R
import com.sam.iotblue.model.Coord
import com.sam.iotblue.utils.AppUtils
import com.sam.iotblue.utils.AppUtils.saveCoordsToList

class MapFragment : Fragment(), OnMapReadyCallback {

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var viewModel: MapViewModel
    private lateinit var mMap: GoogleMap
    var firstTime = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        // TODO: Use the ViewModel
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
//        mapFragment.getMapAsync(this)
    }


    override fun onResume() {
        super.onResume()

        if (!firstTime) {
            mMap.clear()

            val coordsList = AppUtils.getListOfCoords(activity!!.applicationContext)

            if (coordsList.size <= 0) {
                val currentLoc = LatLng(-34.0, 151.0)
                if (activity != null)
                    saveCoordsToList(Coord(currentLoc.latitude, currentLoc.longitude), activity!!.applicationContext)
                mMap.addMarker(MarkerOptions().position(currentLoc))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLoc))
            } else {
                for (i in 0..coordsList.size - 1) {
                    val currentLoc = LatLng(coordsList[i].lat!!, coordsList[i].lon!!)
                    mMap.addMarker(MarkerOptions().position(currentLoc))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLoc))
                }
            }
        } else {
            firstTime = false
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val coordsList = AppUtils.getListOfCoords(activity!!.applicationContext)

        if (coordsList.size <= 0) {
            val currentLoc = LatLng(-34.0, 151.0)
            if (activity != null)
                saveCoordsToList(Coord(currentLoc.latitude, currentLoc.longitude), activity!!.applicationContext)
            mMap.addMarker(MarkerOptions().position(currentLoc))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLoc))
        } else {
            for (i in 0..coordsList.size - 1) {
                val currentLoc = LatLng(coordsList[i].lat!!, coordsList[i].lon!!)
                mMap.addMarker(MarkerOptions().position(currentLoc))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLoc))
            }
        }
        mMap.setOnMapLongClickListener {
            val newCoord = Coord(it.latitude, it.longitude)
            if (activity != null)
                saveCoordsToList(newCoord, activity!!.applicationContext)

            mMap.addMarker(MarkerOptions().position(LatLng(it.latitude, it.longitude)))
            Toast.makeText(activity, "Location Bookmarked", Toast.LENGTH_SHORT).show()
        }
    }
}
