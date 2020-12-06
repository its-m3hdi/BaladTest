package com.itsm3.baladtest.presentation.venues.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.itsm3.baladtest.databinding.FragmentExploreBinding
import com.itsm3.baladtest.di.application.ViewModelFactory
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import com.itsm3.baladtest.presentation.base.BaseFragment
import com.itsm3.baladtest.presentation.common.observe
import com.itsm3.baladtest.presentation.venues.SharedVM
import javax.inject.Inject

class ExploreFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: ExploreListAdapter
    private lateinit var binding: FragmentExploreBinding
    private val sharedVM: SharedVM by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(SharedVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null || !savedInstanceState.containsKey(FRAGMENT_RECREATED))
            sharedVM.explore() // fire it just once at first, will skip in orientation change
        observe(sharedVM.observe(), ::showExploreResult)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.exploreRecycler.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.exploreRecycler.adapter = adapter
    }

    private fun showExploreResult(resultState: @ParameterName(name = "t") ResultState<PagedList<VenuesEntity.Explore>>) {
        when (resultState) {
            is ResultState.Success -> {
                adapter.submitList(resultState.data)
            }
            is ResultState.Loading -> {
                Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
            }
            is ResultState.Fail -> {
                Toast.makeText(context, "Network Fail", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun permissionLocationGranted() {
        sharedVM.explore()
    }
}