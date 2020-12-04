package com.itsm3.baladtest.presentation.venues.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.itsm3.baladtest.databinding.FragmentExploreBinding
import com.itsm3.baladtest.di.application.ViewModelFactory
import com.itsm3.baladtest.presentation.base.BaseFragment
import com.itsm3.baladtest.presentation.venues.SharedVM
import javax.inject.Inject

class ExploreFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val sharedVM: SharedVM by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(SharedVM::class.java)
    }

    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }
}