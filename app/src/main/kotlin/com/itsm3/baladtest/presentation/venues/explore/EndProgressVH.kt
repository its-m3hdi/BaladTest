package com.itsm3.baladtest.presentation.venues.explore

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itsm3.baladtest.R
import com.itsm3.baladtest.databinding.ListItemExploreBinding
import com.itsm3.baladtest.domain.entity.VenuesEntity

class EndProgressVH(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.progressbar, parent, false)) {
}