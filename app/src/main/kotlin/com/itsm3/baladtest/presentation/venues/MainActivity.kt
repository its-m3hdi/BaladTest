package com.itsm3.baladtest.presentation.venues

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.itsm3.baladtest.R
import com.itsm3.baladtest.presentation.base.BaseNavActivity
import com.itsm3.baladtest.presentation.venues.explore.ExploreFragment


class MainActivity : BaseNavActivity() {
    override fun getNavController(): Int =
        R.id.nav_host_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQ_CODE
        )
    }

    override fun onRequestPermissionsResult( // we can handle it in fragment itself too
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQ_CODE)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val fragment = getForegroundFragment()
                if (fragment is ExploreFragment)
                    (fragment as ExploreFragment).permissionLocationGranted()
            }
    }

    companion object {
        const val LOCATION_PERMISSION_REQ_CODE = 73
    }
}