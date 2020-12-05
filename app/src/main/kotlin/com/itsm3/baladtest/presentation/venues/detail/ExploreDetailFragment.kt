package com.itsm3.baladtest.presentation.venues.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import com.itsm3.baladtest.databinding.FragmentExploreDetailBinding
import com.itsm3.baladtest.di.application.ViewModelFactory
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import com.itsm3.baladtest.presentation.base.BaseFragment
import com.itsm3.baladtest.presentation.common.observe
import com.itsm3.baladtest.presentation.venues.SharedVM
import javax.inject.Inject

class ExploreDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentExploreDetailBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val args: ExploreDetailFragmentArgs by navArgs()
    private val sharedVM: SharedVM by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(SharedVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreDetailBinding.inflate(inflater, container, false)
        initView()
        observe(sharedVM.observe(), ::showExploreResult)
        return binding.root
    }

    private fun initView() {
    }

    private fun showExploreResult(resultState: @ParameterName(name = "t") ResultState<PagedList<VenuesEntity.Explore>>) {
        when (resultState) {
            is ResultState.Success -> {
                Toast.makeText(context, "${args.clickedItemId}", Toast.LENGTH_LONG).show()
            }
            is ResultState.Loading -> {
                Toast.makeText(context, "L" + resultState.data.size, Toast.LENGTH_LONG).show()
            }
            is ResultState.Fail -> {
                Toast.makeText(context, "F0", Toast.LENGTH_LONG).show()
            }
        }
    }

}