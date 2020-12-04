package com.itsm3.baladtest.presentation.venues

import android.os.Bundle
import com.itsm3.baladtest.R
import com.itsm3.baladtest.presentation.base.BaseNavActivity

class MainActivity : BaseNavActivity() {
    override fun getNavController(): Int =
        R.id.nav_host_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}