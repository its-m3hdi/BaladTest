package com.itsm3.baladtest.presentation.venues

import android.Manifest
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.itsm3.baladtest.R
import com.itsm3.baladtest.presentation.base.BaseNavActivity
import java.security.Permission

class MainActivity : BaseNavActivity() {
    override fun getNavController(): Int =
        R.id.nav_host_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            73
        )
    }
}