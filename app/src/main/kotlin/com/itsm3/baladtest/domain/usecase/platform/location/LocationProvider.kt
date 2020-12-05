package com.itsm3.baladtest.domain.usecase.platform.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import javax.inject.Inject

class LocationProvider @Inject constructor(private val context: Context) {
    private lateinit var locationCallback: (String?) -> Unit
    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) =
            locationCallback.invoke(location.mapTo())

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
        override fun onProviderEnabled(p0: String?) {}
        override fun onProviderDisabled(p0: String?) {}
    }

    fun Location.mapTo() = "$latitude,$longitude"

    fun getLocation(
        minute: Long,
        distance: Float,
        onLocation: (String?) -> Unit
    ): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )
            return false

        locationCallback = onLocation
        val criteria = Criteria()
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        val isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (isGPSEnable) criteria.accuracy = Criteria.ACCURACY_FINE
        else if (isNetworkEnable) criteria.accuracy = Criteria.ACCURACY_COARSE
        else {
            Log.e("LocationProvider", "no provider available")
            locationCallback.invoke(null)
        }
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (isNetworkEnabled) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minute,
                distance, locationListener, Looper.getMainLooper()
            )

            locationManager.requestSingleUpdate(criteria, object : LocationListener {
                override fun onLocationChanged(location: Location) =
                    locationCallback.invoke(location.mapTo())

                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }, Looper.getMainLooper())
        }
        return true
    }

    fun stopUpdate() {
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.removeUpdates(locationListener)
    }
}