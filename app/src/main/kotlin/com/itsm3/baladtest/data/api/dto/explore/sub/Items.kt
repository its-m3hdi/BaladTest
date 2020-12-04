package com.itsm3.baladtest.data.api.dto.explore.sub

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("venue") val venue: Venue
)