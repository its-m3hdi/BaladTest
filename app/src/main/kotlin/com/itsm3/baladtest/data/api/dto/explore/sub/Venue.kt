package com.itsm3.baladtest.data.api.dto.explore.sub

import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)