package com.itsm3.baladtest.presentation.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseNavActivity : DaggerAppCompatActivity() {
    private val navController: NavController by lazy {
        findNavController(getNavController())
    }

    protected abstract fun getNavController(): Int
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onBackPressed() {
        if (!navController.navigateUp())
            super.onBackPressed()
    }

    fun getForegroundFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(getNavController())
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }
}