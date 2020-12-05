package com.itsm3.baladtest.data.api.dto.explore.sub

import com.google.gson.annotations.SerializedName
import com.itsm3.baladtest.domain.entity.VenuesEntity

data class Items(
    @SerializedName("venue") val venue: Venue
) {
    fun mapTo() = VenuesEntity.Explore(null, venue.name)
}