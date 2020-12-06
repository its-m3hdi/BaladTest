package com.itsm3.baladtest.presentation.venues.explore

import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itsm3.baladtest.domain.entity.VenuesEntity
import javax.inject.Inject

class ExploreListAdapter @Inject constructor() :
    PagedListAdapter<VenuesEntity.Explore, RecyclerView.ViewHolder>(EXPLORE_COMPARATOR) {
    private val PROGRESSBAR = 73

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == PROGRESSBAR)
            return EndProgressVH(parent)
        return ExploreVH.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (itemCount > 1 && position != itemCount-1) // because of progressbar
            with(getItem(position)) {
                this?.let { // because of progressbar
                    when (holder) {
                        is ExploreVH -> holder.bind(this)
                    }
                }
            }
    }

    override fun getItemCount(): Int {
        currentList?.let {
            return it.size + 1
        }
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1)
            return PROGRESSBAR
        return super.getItemViewType(position)
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
