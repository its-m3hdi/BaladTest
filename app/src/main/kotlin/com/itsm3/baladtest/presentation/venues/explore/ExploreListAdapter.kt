package com.itsm3.baladtest.presentation.venues.explore

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.itsm3.baladtest.domain.entity.VenuesEntity
import javax.inject.Inject

class ExploreListAdapter @Inject constructor() :
    PagedListAdapter<VenuesEntity.Explore, ExploreVH>(EXPLORE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreVH =
        ExploreVH.create(parent)

    override fun onBindViewHolder(holder: ExploreVH, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val EXPLORE_COMPARATOR = object : DiffUtil.ItemCallback<VenuesEntity.Explore>() {
            override fun areContentsTheSame(
                oldItem: VenuesEntity.Explore,
                newItem: VenuesEntity.Explore
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(
                oldItem: VenuesEntity.Explore,
                newItem: VenuesEntity.Explore
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
