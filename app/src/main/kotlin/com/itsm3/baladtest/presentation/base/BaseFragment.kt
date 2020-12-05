package com.itsm3.baladtest.presentation.base

import android.os.Bundle
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {
    protected val FRAGMENT_RECREATED = "FRAGMENT_RECREATED"
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FRAGMENT_RECREATED, true)
    }
}