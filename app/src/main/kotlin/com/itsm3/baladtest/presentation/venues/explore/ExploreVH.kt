package com.itsm3.baladtest.presentation.venues.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.itsm3.baladtest.R
import com.itsm3.baladtest.databinding.ListItemExploreBinding
import com.itsm3.baladtest.domain.entity.VenuesEntity

class ExploreVH(private val binding: ListItemExploreBinding, ) :
    RecyclerView.ViewHolder(binding.root) {
    private var id: Int = -1

    init {
        binding.root.setOnClickListener {
            val direction =
                ExploreFragmentDirections.navToExploreDetailFrag(id.toLong())
            it.findNavController().navigate(direction)
        }
    }

    fun bind(item: VenuesEntity.Explore?) {
        id = item?.id!!
        binding.explore = item
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): ExploreVH {

            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_item_explore, parent, false
            ) as ListItemExploreBinding

            return ExploreVH(
                binding
            )
        }
    }
}