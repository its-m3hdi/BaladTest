package com.itsm3.baladtest.data.api.dto.explore.sub

import com.google.gson.annotations.SerializedName

data class Groups(
    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("items") val items: List<Items>
)